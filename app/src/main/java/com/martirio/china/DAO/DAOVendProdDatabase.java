package com.martirio.china.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.martirio.china.Modelo.Producto;
import com.martirio.china.Modelo.Vendedor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOVendProdDatabase extends DatabaseHelper {

   public static final String IDPRODUCTO = "IDPRODUCTO";
   public static final String IDVENDEDOR = "IDVENDEDOR";
   public static final String TABLE_VENDPROD = "TABLE_VENDPROD";

    public DAOVendProdDatabase(Context context) {
        super(context);
    }

    public void agregarProductoVendedor(Producto unProducto, Vendedor unVendedor){

            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();
            row.put(IDPRODUCTO, unProducto.getIdProducto());
            row.put(IDVENDEDOR, unVendedor.getIdVendedor());
            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_VENDPROD, null, row);
            database.close();
    }

    public void agregarMuchosProductosVendedor(List<Producto> listaProductos, Vendedor unVendedor){
        for(Producto unProducto : listaProductos){
            agregarProductoVendedor(unProducto,unVendedor);
        }
    }

    public List<String> traerProductosPorVendedor(Vendedor unVendedor){

        List<String> listaProductoPorVendedor  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_VENDPROD+" WHERE IDVENDEDOR = '"+ unVendedor.getIdVendedor()+"'";
        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){
        //LEER CADA FILA DE LA TABLA RESULTADO
        listaProductoPorVendedor.add(cursor.getString(cursor.getColumnIndex(IDPRODUCTO)));
        }
        //CERRAR
        cursor.close();
        database.close();
        return listaProductoPorVendedor;
    }
}
