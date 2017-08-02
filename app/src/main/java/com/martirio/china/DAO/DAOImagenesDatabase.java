package com.martirio.china.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.security.keystore.UserNotAuthenticatedException;

import com.martirio.china.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOImagenesDatabase extends DatabaseHelper {

   public static final String IDPRODUCTO = "IDPRODUCTO";
   public static final String FOTOPRODUCTO = "FOTOPRODUCTO";
   public static final String TABLE_IMAGENES = "TABLE_IMAGENES";

    public DAOImagenesDatabase(Context context) {
        super(context);
    }

    public void agregarImagen(String unaFoto, Producto unProducto){

            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();
            row.put(IDPRODUCTO, unProducto.getNombreProducto());
            row.put(FOTOPRODUCTO, unaFoto);
            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_IMAGENES, null, row);
            database.close();
    }

    public void agregarFotosVarias(List<String> fotosLista, Producto unProducto){
        for(String unString : fotosLista){
            agregarImagen(unString,unProducto);
        }
    }

    public List<String> traerFotosProducto(Producto unProducto){

        List<String> listaFotosProducto  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_IMAGENES+" WHERE IDPRODUCTO = '"+ unProducto.getIdProducto()+"'";
        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){
        //LEER CADA FILA DE LA TABLA RESULTADO
        listaFotosProducto.add(cursor.getString(cursor.getColumnIndex(FOTOPRODUCTO)));
        }
        //CERRAR
        cursor.close();
        database.close();
        return listaFotosProducto;
    }
}
