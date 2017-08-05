package com.martirio.china.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String PRODUCTOS_DB = "productosDB";
    public static final Integer VERSION_DB = 1;


    private Context context;

    //DEFINE LA BASE DE DATOS CON EL NOMBRE personDB y con version 1
    public DatabaseHelper(Context context) {
        super(context, PRODUCTOS_DB, null, VERSION_DB);
    }

    //ACA ADENTRO DEFINIMOS LA ESTRUCTURA QUE VA A TENER MI BD EN VERSION 1
    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREO TABLA DE PRODUCTOS
        String query =  "CREATE TABLE " + DAOProductosDatabase.TABLE_PRODUCTOS  + "(" +
                DAOProductosDatabase.NOMBREPRODUCTO + " TEXT, " +
                DAOProductosDatabase.DESCRIPCION + " TEXT, " +
                DAOProductosDatabase.IDVENDEDOR + " TEXT, " +
                DAOProductosDatabase.PESOBULTO + " REAL, " +
                DAOProductosDatabase.CANTIDADBULTO + " FLOAT, " +
                DAOProductosDatabase.PRECIOBULTO + " REAL, " +
                DAOProductosDatabase.MONEDABULTO + " TEXT, " +
                DAOProductosDatabase.ANCHOBULTO + " INTEGER, " +
                DAOProductosDatabase.ALTOBULTO + " INTEGER, " +
                DAOProductosDatabase.PROFUNDIDADBULTO + " INTEGER, " +
                DAOProductosDatabase.IDPRODUCTO + " TEXT PRIMARY KEY, " +
                DAOProductosDatabase.PRECIOUNITARIO + " REAL )";
        db.execSQL(query);

        //CREO TABLA DE VENDEDORES
        String query2 =  "CREATE TABLE " + DAOVendedorDatabase.TABLE_VENDEDORES  + "(" +
                DAOVendedorDatabase.NOMBREVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.MAILVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.TELEFONOVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.FOTOVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.IDVENDEDOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DAOVendedorDatabase.OBSERVACIONVENDEDOR + " TEXT )";
        db.execSQL(query2);

        //CREO TABLA DE VENDEDORES
        String query3 =  "CREATE TABLE " + DAOImagenesDatabase.TABLE_IMAGENES  + "(" +
                DAOVendedorDatabase.NOMBREVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.MAILVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.TELEFONOVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.FOTOVENDEDOR + " TEXT, " +
                DAOVendedorDatabase.IDVENDEDOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DAOVendedorDatabase.OBSERVACIONVENDEDOR + " TEXT )";
        db.execSQL(query3);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }







}
