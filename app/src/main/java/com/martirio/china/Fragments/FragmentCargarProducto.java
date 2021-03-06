package com.martirio.china.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.martirio.china.Activities.MainActivity;
import com.martirio.china.Adapters.AdapterFotos;
import com.martirio.china.DAO.DAOImagenesDatabase;
import com.martirio.china.DAO.DAOProductosDatabase;
import com.martirio.china.DAO.DAOVendProdDatabase;
import com.martirio.china.DAO.DAOVendedorDatabase;
import com.martirio.china.Modelo.Foto;
import com.martirio.china.Modelo.Orden;
import com.martirio.china.Modelo.Producto;
import com.martirio.china.Modelo.Vendedor;
import com.martirio.china.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;


public class FragmentCargarProducto extends Fragment implements AdapterView.OnItemSelectedListener {
    private DAOProductosDatabase daoProductosDatabase;
    private DAOImagenesDatabase daoImagenesDatabase;
    private DAOVendedorDatabase daoVendedorDatabase;
    private DAOVendProdDatabase daoVendProdDatabase;

    private RecyclerView recyclerFotos;
    private RealmList<Foto> listaFotos;
    private AdapterFotos adapterFotos;
    private LinearLayoutManager layoutManager;

    private ImageView fotoVendedor;

    private Integer control;

    private EditText nombreProducto;
    private EditText cantidadBulto;
    private EditText pesoBulto;
    private EditText precioBulto;
    private EditText divisa;
    private EditText anchoBulto;
    private EditText altoBulto;
    private EditText profundidadBulto;
    private EditText observacionesBulto;
    private EditText nombreVendedor;
    private EditText mailVendedor;
    private EditText telefonoVendedor;
    private EditText observacionVendedor;
    
    private TextInputLayout inputNombreProducto;
    private TextInputLayout inputCantidadBulto;
    private TextInputLayout inputPesoBulto;
    private TextInputLayout inputPrecioBulto;
    private TextInputLayout inputDivisa;
    private TextInputLayout inputAnchoBulto;
    private TextInputLayout inputProfundidadBulto;
    private TextInputLayout InputObservacionesBulto;
    
    private TextInputLayout inputNombreVendedor;
    private TextInputLayout inputMailVendedor;
    private TextInputLayout inputTelefonoVendedor;
    private TextInputLayout inputObservacionVendedor;
    private TextInputLayout inputAltoBulto;

    private FloatingActionButton fabFotoProducto;
    private FloatingActionButton fabFotoVendedor;

    private FloatingActionMenu fabMenuCargaProductos;

    private FloatingActionButton guardarOrden;
    private FloatingActionButton agregarProducto;
    private FloatingActionButton agregarVendedor;
    private FloatingActionButton salir;
    private String stringFotoVendedor;

    public FragmentCargarProducto() {    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.cargar_producto_fragment, container, false);

        daoImagenesDatabase= new DAOImagenesDatabase(getContext());
        daoProductosDatabase= new DAOProductosDatabase(getContext());
        daoVendedorDatabase= new DAOVendedorDatabase(getContext());
        daoVendProdDatabase= new DAOVendProdDatabase(getContext());

        nombreProducto = (EditText) view.findViewById(R.id.textNombreProducto);
        cantidadBulto = (EditText) view.findViewById(R.id.textCantidadBulto);
        pesoBulto = (EditText) view.findViewById(R.id.textPesoBulto);
        precioBulto = (EditText) view.findViewById(R.id.textoPrecioBulto);
        divisa = (EditText) view.findViewById(R.id.textoDivisaBulto);
        anchoBulto = (EditText) view.findViewById(R.id.textoAnchoBulto);
        altoBulto = (EditText) view.findViewById(R.id.textoAltoBulto);
        profundidadBulto = (EditText) view.findViewById(R.id.textoProfundidadBulto);
        observacionesBulto = (EditText) view.findViewById(R.id.textoObservacionProducto);
        nombreVendedor = (EditText) view.findViewById(R.id.textoNombreVendedor);
        mailVendedor = (EditText) view.findViewById(R.id.textoMailVendedor);
        telefonoVendedor = (EditText) view.findViewById(R.id.textoTelefonoVendedor);
        observacionVendedor = (EditText) view.findViewById(R.id.textoObservacionVendedor);

        inputNombreProducto=(TextInputLayout) view.findViewById(R.id.inputNombreProducto);
        inputCantidadBulto=(TextInputLayout) view.findViewById(R.id.inputCantidadBulto);
        inputPesoBulto=(TextInputLayout) view.findViewById(R.id.inputPesoBulto);
        inputPrecioBulto=(TextInputLayout) view.findViewById(R.id.inputPrecioBulto);
        inputDivisa=(TextInputLayout) view.findViewById(R.id.inputDivisaBulto);
        inputAnchoBulto=(TextInputLayout) view.findViewById(R.id.inputAnchoBulto);
        inputProfundidadBulto=(TextInputLayout) view.findViewById(R.id.inputProfundidadBulto);
        InputObservacionesBulto=(TextInputLayout) view.findViewById(R.id.inputObservacionProducto);
        inputNombreVendedor=(TextInputLayout) view.findViewById(R.id.inputNombreVendedor);
        inputMailVendedor=(TextInputLayout) view.findViewById(R.id.inputMailVendedor);
        inputTelefonoVendedor=(TextInputLayout) view.findViewById(R.id.inputTelefonoVendedor);
        inputObservacionVendedor=(TextInputLayout) view.findViewById(R.id.inputObservacionVendedor);
        inputAltoBulto=(TextInputLayout) view.findViewById(R.id.inputAltoBulto);

        fotoVendedor=(ImageView)view.findViewById(R.id.imageVendedor);

        recyclerFotos=(RecyclerView)view.findViewById(R.id.recyclerFotoProductos);
        listaFotos=new RealmList<>();
        layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerFotos.setLayoutManager(layoutManager);
        adapterFotos=new AdapterFotos();
        adapterFotos.setContext(getContext());
        adapterFotos.setListaFotosOriginales(listaFotos);
        recyclerFotos.setAdapter(adapterFotos);

        fabMenuCargaProductos=(FloatingActionMenu)view.findViewById(R.id.fabMenuCargaProductos);

        fabFotoProducto=(FloatingActionButton) view.findViewById(R.id.fabFotoProducto);
        fabFotoProducto.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        fabFotoProducto.setButtonSize(FloatingActionButton.SIZE_NORMAL);
        fabFotoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openCamera(FragmentCargarProducto.this, 1);
            }
        });

        fabFotoVendedor=(FloatingActionButton) view.findViewById(R.id.fabCamaraVendedor);
        fabFotoVendedor.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        fabFotoVendedor.setButtonSize(FloatingActionButton.SIZE_NORMAL);
        fabFotoVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openCamera(FragmentCargarProducto.this, 2);
            }
        });

        guardarOrden = new FloatingActionButton(getActivity());
        guardarOrden.setButtonSize(FloatingActionButton.SIZE_MINI);
        guardarOrden.setLabelText(getString(R.string.CargarOrden));
        guardarOrden.setImageResource(R.drawable.ic_save_black_24dp);
        fabMenuCargaProductos.addMenuButton(guardarOrden);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            guardarOrden.setLabelColors(ContextCompat.getColor(getActivity(), R.color.rojoOscuro),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            guardarOrden.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
        else {
            guardarOrden.setLabelColors(getResources().getColor(R.color.rojoOscuro),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            guardarOrden.setLabelTextColor(getResources().getColor( R.color.black));
        }
        
        

        guardarOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenuCargaProductos.close(true);
                cargarDatos();
                if (control>0){
                }
                else{
                volverAMain();
                }
            }
            
        });

        agregarProducto = new FloatingActionButton(getActivity());
        agregarProducto.setButtonSize(FloatingActionButton.SIZE_MINI);
        agregarProducto.setLabelText(getString(R.string.nuevoProducto));
        agregarProducto.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);
        fabMenuCargaProductos.addMenuButton(agregarProducto);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            agregarProducto.setLabelColors(ContextCompat.getColor(getActivity(), R.color.rojoOscuro),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            agregarProducto.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
        else {
            agregarProducto.setLabelColors(getResources().getColor(R.color.rojoOscuro),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            agregarProducto.setLabelTextColor(getResources().getColor( R.color.black));
        }



        agregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenuCargaProductos.close(true);
                cargarDatos();
                if (control>0){}
                else{
                    borrarProducto();
                }

            }

        });


        agregarVendedor = new FloatingActionButton(getActivity());
        agregarVendedor.setButtonSize(FloatingActionButton.SIZE_MINI);
        agregarVendedor.setLabelText(getString(R.string.agregarVendedor));
        agregarVendedor.setImageResource(R.drawable.ic_person_add_black_24dp);
        fabMenuCargaProductos.addMenuButton(agregarVendedor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            agregarVendedor.setLabelColors(ContextCompat.getColor(getActivity(), R.color.rojoOscuro),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            agregarVendedor.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
        else {
            agregarVendedor.setLabelColors(getResources().getColor(R.color.rojoOscuro),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            agregarVendedor.setLabelTextColor(getResources().getColor( R.color.black));
        }



        agregarVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenuCargaProductos.close(true);
                cargarDatos();
                if (control > 0) {
                } else {
                    borrarTodo();
                }
            }

        });


        salir = new FloatingActionButton(getActivity());
        salir.setButtonSize(FloatingActionButton.SIZE_MINI);
        salir.setLabelText(getString(R.string.salir));
        salir.setImageResource(R.drawable.ic_exit_to_app_black_24dp);
        fabMenuCargaProductos.addMenuButton(salir);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            salir.setColorNormal(ContextCompat.getColor(getActivity(), R.color.Amarillo));
            salir.setLabelColors(ContextCompat.getColor(getActivity(), R.color.Amarillo),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            salir.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
        else {
            salir.setColorNormal(getResources().getColor(R.color.Amarillo));
            salir.setLabelColors(getResources().getColor(R.color.Amarillo),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            salir.setLabelTextColor(getResources().getColor( R.color.black));
        }



        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMain();
            }

        });

        return view;
    }

    private void avisarFaltanDatos() {
        Toast.makeText(getContext(), "Error, revise los datos cargados.", Toast.LENGTH_SHORT).show();
    }

    public void cargarDatos(){
        //controlar los input layout
        //cargar los datos
        Producto unProducto = new Producto();
        Vendedor unVendedor= new Vendedor();
        control=0;

        unProducto.setIdProducto("PRODUCTO-" + UUID.randomUUID().toString());
        unVendedor.setIdVendedor("VENDEDOR-"+UUID.randomUUID().toString());

        //comprobaciones producto
        if (altoBulto.getText()==null||altoBulto.getText().toString().isEmpty()){
            unProducto.setAltoBulto(0);
        }
        else {
            unProducto.setAltoBulto(Integer.parseInt(altoBulto.getText().toString()));
        }

        if (anchoBulto.getText()==null || anchoBulto.getText().toString().isEmpty()){
            unProducto.setAnchoBulto(0);
        }
        else{
            unProducto.setAnchoBulto(Integer.parseInt(anchoBulto.getText().toString()));
        }
        if (profundidadBulto.getText()==null||profundidadBulto.getText().toString().isEmpty()){
            unProducto.setProfundidadBulto(0);
        }
        else{
            unProducto.setProfundidadBulto(Integer.parseInt(profundidadBulto.getText().toString()));
        }
        if (cantidadBulto.getText()==null||cantidadBulto.getText().toString().isEmpty()){
            unProducto.setCantidadBulto(0);
        }
        else{
            unProducto.setCantidadBulto(Integer.parseInt(cantidadBulto.getText().toString()));
        }
        if (nombreProducto.getText()==null||nombreProducto.getText().toString().isEmpty()){
            control=control+1;
            inputNombreProducto.setError("Debe ingresar un nombre de producto");

        }
        else{
            unProducto.setNombreProducto(nombreProducto.getText().toString());
        }


        if (divisa.getText()==null||divisa.getText().toString().isEmpty()){
            unProducto.setMonedaBulto("USD");
        }
        else{
            unProducto.setMonedaBulto(divisa.getText().toString());
        }
        if (pesoBulto.getText()==null || pesoBulto.getText().toString().isEmpty()){
            unProducto.setPesoBulto(0);
        }
        else{
            unProducto.setPesoBulto(Integer.parseInt(pesoBulto.getText().toString()));
        }
        if (precioBulto.getText()==null||precioBulto.getText().toString().isEmpty()){
            control=control+1;
            inputPrecioBulto.setError("Debe ingresar un precio por bulto");
        }
        else{
            unProducto.setPrecioBulto(Double.parseDouble(precioBulto.getText().toString()));
        }
        if (listaFotos.size()<1){
            unProducto.setFotos(new RealmList<Foto>());
        }
        else{
            unProducto.setFotos(listaFotos);
        }

        //comprobaciones vendedor

        if (stringFotoVendedor==null||stringFotoVendedor.isEmpty()){
            stringFotoVendedor="SinFoto";
        }
        else{
            unVendedor.setFotoVendedor(stringFotoVendedor);
        }
        if (mailVendedor.getText()==null||mailVendedor.getText().toString().isEmpty()){
            inputMailVendedor.setError("Debe ingresar un mail para el vendedor");
        }
        else {
            unVendedor.setMailVendedor(mailVendedor.getText().toString());
        }
        if (nombreVendedor.getText()==null || nombreVendedor.getText().toString().isEmpty()){
            inputNombreVendedor.setError("Debe ingresar un vendedor");
            control=control+1;
        }
        else {
            unVendedor.setNombreVendedor(nombreVendedor.getText().toString());
        }
        if (telefonoVendedor.getText()==null||telefonoVendedor.getText().toString().isEmpty()){
            unVendedor.setTelefonoVendedor("n/a");
        }
        else {
            unVendedor.setTelefonoVendedor(telefonoVendedor.getText().toString());
        }

        //chequeo si hubo errores
        if (control>0){
          avisarFaltanDatos();
        }
        else{
            Orden unaOrden=new Orden();
            unaOrden.setProducto(unProducto);
            unaOrden.setVendedor(unVendedor);
            unaOrden.setId("orden-"+UUID.randomUUID());
            // Obtain a Realm instance
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            Orden realmOrden = realm.copyToRealm(unaOrden);
            realm.commitTransaction();
            

        }


    }
    public void volverAMain(){
        Intent unIntent= new Intent(getActivity(), MainActivity.class);
        getActivity().finish();
    }


    @Override
    public void onActivityResult( int  requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                if (type==1){
                    Foto unaFoto=new Foto();
                    unaFoto.setFoto(imageFile.getAbsolutePath());
                     listaFotos.add(unaFoto);
                     adapterFotos.notifyDataSetChanged();
                }
                if (type==2){

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
                    fotoVendedor.setImageBitmap(bitmap);
                    stringFotoVendedor= imageFile.getAbsolutePath();
                }

            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getActivity());
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "hola que tal ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
    public void borrarTodo(){
        nombreProducto.setText("");
        cantidadBulto.setText("");
        pesoBulto.setText("");
        precioBulto.setText("");
        divisa.setText("");
        anchoBulto.setText("");
        altoBulto.setText("");
        profundidadBulto.setText("");
        observacionesBulto.setText("");
        nombreVendedor.setText("");
        mailVendedor.setText("");
        telefonoVendedor.setText("");
        observacionVendedor.setText("");

        inputNombreProducto.setError("");
        inputCantidadBulto.setError("");
        inputPesoBulto.setError("");
        inputPrecioBulto.setError("");
        inputDivisa.setError("");
        inputAnchoBulto.setError("");
        inputProfundidadBulto.setError("");
        InputObservacionesBulto.setError("");
        inputNombreVendedor.setError("");
        inputMailVendedor.setError("");
        inputTelefonoVendedor.setError("");
        inputObservacionVendedor.setError("");
        inputAltoBulto.setError("");

        listaFotos=new RealmList<>();
        adapterFotos.setListaFotosOriginales(listaFotos);
        adapterFotos.notifyDataSetChanged();
        fotoVendedor.setImageDrawable(null);
        nombreProducto.requestFocus();
    }
    public void borrarProducto(){
        nombreProducto.setText("");
        cantidadBulto.setText("");
        pesoBulto.setText("");
        precioBulto.setText("");
        divisa.setText("");
        anchoBulto.setText("");
        altoBulto.setText("");
        profundidadBulto.setText("");
        observacionesBulto.setText("");

        inputNombreProducto.setError("");
        inputCantidadBulto.setError("");
        inputPesoBulto.setError("");
        inputPrecioBulto.setError("");
        inputDivisa.setError("");
        inputAnchoBulto.setError("");
        inputProfundidadBulto.setError("");
        InputObservacionesBulto.setError("");

        listaFotos=new RealmList<>();
        adapterFotos.setListaFotosOriginales(listaFotos);
        adapterFotos.notifyDataSetChanged();
        nombreProducto.requestFocus();
    }
}

