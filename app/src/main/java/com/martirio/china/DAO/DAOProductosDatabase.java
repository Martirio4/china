package com.martirio.china.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.martirio.china.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOProductosDatabase extends DatabaseHelper {

   public static final String NOMBREPRODUCTO = "NOMBREPRODUCTO";
   public static final String DESCRIPCION = "DESCRIPCION";
   public static final String IDVENDEDOR = "IDVENDEDOR";
   public static final String PESOBULTO= "PESOBULTO";
   public static final String CANTIDADBULTO = "CANTIDADBULTO";
   public static final String PRECIOBULTO = "PRECIOBULTO";
   public static final String MONEDABULTO = "MONEDABULTO";
   public static final String ANCHOBULTO = "ANCHOBULTO";
   public static final String ALTOBULTO = "ALTOBULTO";
   public static final String PROFUNDIDADBULTO = "PROFUNDIDADBULTO";
   public static final String IDPRODUCTO = "IDPRODUCTO";
   public static final String PRECIOUNITARIO = "PRECIOUNITARIO";
   public static final String TABLE_PRODUCTOS = "TABLE_PRODUCTOS";

    public DAOProductosDatabase(Context context) {
        super(context);
    }

    public void agregarProducto(Producto unProducto){



            SQLiteDatabase database = getWritableDatabase();

            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();
            row.put(NOMBREPRODUCTO, unProducto.getNombreProducto());
            row.put(DESCRIPCION, unProducto.getDescripcion());
            row.put(PESOBULTO, unProducto.getPesoBulto());
            row.put(CANTIDADBULTO, unProducto.getCantidadBulto());
            row.put(PRECIOBULTO,unProducto.getPrecioBulto());
            row.put(MONEDABULTO, unProducto.getMonedaBulto());
            row.put(ANCHOBULTO, unProducto.getAnchoBulto());
            row.put(ALTOBULTO, unProducto.getAltoBulto());
            row.put(PROFUNDIDADBULTO, unProducto.getProfundidadBulto());
            row.put(IDPRODUCTO, unProducto.getIdProducto());
            row.put(PRECIOUNITARIO, unProducto.getPrecioUnitario());

            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_PRODUCTOS, null, row);

            database.close();

    }

    public void addProductosVarios(List<Producto> formatosList){

        for(Producto unProducto : formatosList){
            agregarProducto(unProducto);
        }
    }


    public List<Producto> getAllFormatosPorVendedor(String idVendedor){

        List<Producto> listaProductosPorVendedor  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_PRODUCTOS+" WHERE IDVENDEDOR = '"+idVendedor+"'";

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setNombreProducto(cursor.getString(cursor.getColumnIndex(NOMBREPRODUCTO)));
            unProducto.setDescripcion(cursor.getString(cursor.getColumnIndex(DESCRIPCION)));
            unProducto.setDescripcion(cursor.getString(cursor.getColumnIndex(IDVENDEDOR)));
            unProducto.setPesoBulto(cursor.getInt(cursor.getColumnIndex(PESOBULTO)));
            unProducto.setPrecioUnitario(cursor.getDouble(cursor.getColumnIndex(PRECIOUNITARIO)));
            unProducto.setPrecioBulto(cursor.getDouble(cursor.getColumnIndex(PRECIOBULTO)));
            unProducto.setCantidadBulto(cursor.getInt(cursor.getColumnIndex(CANTIDADBULTO)));
            unProducto.setAltoBulto(cursor.getInt(cursor.getColumnIndex(ALTOBULTO)));
            unProducto.setAnchoBulto(cursor.getInt(cursor.getColumnIndex(ANCHOBULTO)));
            unProducto.setProfundidadBulto(cursor.getInt(cursor.getColumnIndex(PROFUNDIDADBULTO)));
            unProducto.setIdProducto(cursor.getString(cursor.getColumnIndex(IDPRODUCTO)));
            unProducto.setMonedaBulto(cursor.getString(cursor.getColumnIndex(MONEDABULTO)));
            listaProductosPorVendedor.add(unProducto);
        }
        //CERRAR
        cursor.close();
        database.close();

        return listaProductosPorVendedor;
    }


    public Producto getProducto(String idProducto){

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE IDPRODUCTO=" + idProducto;

        Cursor cursor = database.rawQuery(query, null);
        Producto unProducto = null;
        if(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO

            unProducto = new Producto();
            unProducto.setNombreProducto(cursor.getString(cursor.getColumnIndex(NOMBREPRODUCTO)));
            unProducto.setDescripcion(cursor.getString(cursor.getColumnIndex(DESCRIPCION)));
            unProducto.setDescripcion(cursor.getString(cursor.getColumnIndex(IDVENDEDOR)));
            unProducto.setPesoBulto(cursor.getInt(cursor.getColumnIndex(PESOBULTO)));
            unProducto.setPrecioUnitario(cursor.getDouble(cursor.getColumnIndex(PRECIOUNITARIO)));
            unProducto.setPrecioBulto(cursor.getDouble(cursor.getColumnIndex(PRECIOBULTO)));
            unProducto.setCantidadBulto(cursor.getInt(cursor.getColumnIndex(CANTIDADBULTO)));
            unProducto.setAltoBulto(cursor.getInt(cursor.getColumnIndex(ALTOBULTO)));
            unProducto.setAnchoBulto(cursor.getInt(cursor.getColumnIndex(ANCHOBULTO)));
            unProducto.setProfundidadBulto(cursor.getInt(cursor.getColumnIndex(PROFUNDIDADBULTO)));
            unProducto.setIdProducto(cursor.getString(cursor.getColumnIndex(IDPRODUCTO)));
            unProducto.setMonedaBulto(cursor.getString(cursor.getColumnIndex(MONEDABULTO)));

        }

        cursor.close();
        database.close();

        return unProducto;
}


}
