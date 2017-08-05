package com.martirio.china.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.martirio.china.Modelo.Vendedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOVendedorDatabase extends DatabaseHelper {

   public static final String NOMBREVENDEDOR = "NOMBREVENDEDOR";
   public static final String MAILVENDEDOR = "MAILVENDEDOR";
   public static final String IDVENDEDOR= "IDVENDEDOR";
   public static final String TELEFONOVENDEDOR = "TELEFONOVENDEDOR";
   public static final String FOTOVENDEDOR = "FOTOVENDEDOR";
   public static final String OBSERVACIONVENDEDOR = "OBSERVACIONVENDEDOR";
   public static final String TABLE_VENDEDORES = "TABLE_VENDEDORES";

    public DAOVendedorDatabase(Context context) {
        super(context);
    }

    public void addVendedor (Vendedor unVendedor){

            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();
            row.put(NOMBREVENDEDOR, unVendedor.getNombreVendedor());
            row.put(MAILVENDEDOR, unVendedor.getMailVendedor());
            row.put(IDVENDEDOR, unVendedor.getIdVendedor());
            row.put(TELEFONOVENDEDOR, unVendedor.getTelefonoVendedor());
            row.put(FOTOVENDEDOR,unVendedor.getFotoVendedor());
            row.put(OBSERVACIONVENDEDOR, unVendedor.getObservacionVendedor());
            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_VENDEDORES, null, row);
            database.close();

    }

    public List<Vendedor> traerVendedores(){

        List<Vendedor> listaVendedores  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_VENDEDORES;

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Vendedor unVendedor = new Vendedor();
            unVendedor.setNombreVendedor(cursor.getString(cursor.getColumnIndex(NOMBREVENDEDOR)));
            unVendedor.setMailVendedor(cursor.getString(cursor.getColumnIndex(MAILVENDEDOR)));
            unVendedor.setTelefonoVendedor(cursor.getString(cursor.getColumnIndex(TELEFONOVENDEDOR)));
            unVendedor.setObservacionVendedor(cursor.getString(cursor.getColumnIndex(OBSERVACIONVENDEDOR)));
            unVendedor.setIdVendedor(cursor.getString(cursor.getColumnIndex(IDVENDEDOR)));
            unVendedor.setFotoVendedor(cursor.getString(cursor.getColumnIndex(FOTOVENDEDOR)));
            listaVendedores.add(unVendedor);
        }
        //CERRAR
        cursor.close();
        database.close();
        return listaVendedores;
    }

    public Vendedor getVendedor(String idVendedor){

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_VENDEDORES+
                        " WHERE IDVENDEDOR=" + idVendedor;

        Cursor cursor = database.rawQuery(query, null);
        Vendedor unVendedor = null;
        if(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO

            unVendedor = new Vendedor();
            unVendedor.setNombreVendedor(cursor.getString(cursor.getColumnIndex(NOMBREVENDEDOR)));
            unVendedor.setMailVendedor(cursor.getString(cursor.getColumnIndex(MAILVENDEDOR)));
            unVendedor.setTelefonoVendedor(cursor.getString(cursor.getColumnIndex(TELEFONOVENDEDOR)));
            unVendedor.setFotoVendedor(cursor.getString(cursor.getColumnIndex(FOTOVENDEDOR)));
            unVendedor.setIdVendedor(cursor.getString(cursor.getColumnIndex(IDVENDEDOR)));
            unVendedor.setObservacionVendedor(cursor.getString(cursor.getColumnIndex(OBSERVACIONVENDEDOR)));
        }

        cursor.close();
        database.close();
        return unVendedor;
    }

    public Boolean checkIfExist(String idVendedor){
        Vendedor unVendedor = getVendedor(idVendedor);
        return !(unVendedor == null);
    }
}
