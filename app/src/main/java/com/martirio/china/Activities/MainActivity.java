package com.martirio.china.Activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.martirio.china.Fragments.FragmentCargarProducto;
import com.martirio.china.Modelo.Orden;
import com.martirio.china.R;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Button botonCargarOrden;
    private Button botonVerOrden;
    private Button botonOpcion3;
    private Button botonOpcion4;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        botonCargarOrden= (Button)findViewById(R.id.btn_main_cargarOrden);
        botonVerOrden= (Button) findViewById(R.id.btn_main_verOrden);
        botonOpcion3=(Button) findViewById(R.id.btn_main_3opcion);
        botonOpcion4=(Button) findViewById(R.id.btn_main_4opcion);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        botonCargarOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent= new Intent(MainActivity.this, ActivityCargarProductos.class);
                startActivity(unIntent);
            }
        });

        botonVerOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent= new Intent(MainActivity.this, ActivityVerProductos.class);
                startActivity(unIntent);
            }
        });
        botonOpcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MandarMailAsincrono().execute();

            }
        });

        Realm.init(this);
    }

    public void mandarMail() {


        Realm realm = Realm.getDefaultInstance();
        RealmResults<Orden> result2 = realm.where(Orden.class)
                .findAll();

        Integer cantidadRegistros=result2.size();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Lista Productos"));
        for (int i=0;i<cantidadRegistros;i++) {
            Row row = sheet.createRow(i);
            Cell cell1 = row.createCell(0);
            Cell cell2 =row.createCell(1);
            Cell cell3 =row.createCell(2);
            cell1.setCellValue(result2.get(i).getId());
            cell2.setCellValue(result2.get(i).getProducto().getNombreProducto());
            cell3.setCellValue(result2.get(i).getVendedor().getMailVendedor());
        }
        String outFileName = "filetoshare.xlsx";
        try {

            File cacheDir = getExternalCacheDir();
            File outFile = new File(cacheDir, outFileName);
            String rutaArchivo=outFile.getAbsolutePath();
            OutputStream outputStream = new FileOutputStream(outFile.getAbsolutePath());
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();





            ArrayList<Uri> uris = new ArrayList<>();
            uris.add(Uri.parse("file://" + rutaArchivo));
//filepath is something like that: /mnt/sdcard/DCIM/DSC0001.JPG
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "example@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Productos Relevados");
            List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(emailIntent, 0);
            List<LabeledIntent> intents = new ArrayList<>();
            for (ResolveInfo info : resolveInfos) {
                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Productos Relevados");
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris); //ArrayList<Uri> of attachment Uri's
                intents.add(new LabeledIntent(intent, info.activityInfo.packageName, info.loadLabel(getPackageManager()), info.icon));
            }
            Intent chooser = Intent.createChooser(intents.remove(intents.size() - 1), "Enviar datos a destinatario de correo");
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new LabeledIntent[intents.size()]));
            startActivityForResult(chooser,1);







        } catch (Exception e) {
            /* proper exception handling to be here */

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private class MandarMailAsincrono extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {
            mandarMail();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
