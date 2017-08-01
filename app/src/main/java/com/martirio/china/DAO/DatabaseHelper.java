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

        //CREO TABLA DE FORMATOS
        String query =  "CREATE TABLE " + DAOProductosDatabase.TABLE_PRODUCTOS  + "(" +
                DAOProductosDatabase.NOMBREPRODUCTO + " TEXT, " +
                DAOProductosDatabase.DESCRIPCION + " TEXT, " +
                DAOProductosDatabase.PESOBULTO + " REAL, " +
                DAOProductosDatabase.CANTIDADBULTO + " FLOAT, " +
                DAOProductosDatabase.PRECIOBULTO + " REAL, " +
                DAOProductosDatabase.MONEDABULTO + " TEXT, " +
                DAOProductosDatabase.ANCHOBULTO + " INTEGER, " +
                DAOProductosDatabase.ALTOBULTO + " INTEGER, " +
                DAOProductosDatabase.PROFUNDIDADBULTO + " INTEGER, " +
                DAOProductosDatabase.IDPRODUCTO + " TEXT PRIMARY KEY, " +
                DAOProductosDatabase.PRECIOUNITARIO + " REAL, ";
        db.execSQL(query);

        //CREO TABLA DE USUARIOS
        String query2 =  "CREATE TABLE " + DAOUsuarioDatabase.TABLE_USUARIOS  + "(" +
                DAOUsuarioDatabase.USER + " TEXT, " +
                DAOUsuarioDatabase.PASS + " TEXT, " +
                DAOUsuarioDatabase.PAIS + " TEXT, " +
                DAOUsuarioDatabase.MAIL + " TEXT, " +
                DAOUsuarioDatabase.IDIOMA + " TEXT, " +
                DAOUsuarioDatabase.IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DAOUsuarioDatabase.FECHANACIMIENTO + " TEXT )";
        db.execSQL(query2);

        //CREAR TABLA DE FAVORITOS
        String query3 =  "CREATE TABLE " + DAOFavoritosDatabase.TABLE_FAVORITOS  + "(" +
                DAOFavoritosDatabase.TITLE + " TEXT, " +
                DAOFavoritosDatabase.MAIL + " TEXT, " +
                DAOFavoritosDatabase.RELEASE_DATE + " TEXT, " +
                DAOFavoritosDatabase.OVERVIEW + " TEXT, " +
                DAOFavoritosDatabase.VOTE_AVERAGE + " FLOAT, " +
                DAOFavoritosDatabase.TIPO_FORMATO + " TEXT, " +
                DAOFavoritosDatabase.POSTER_PATH + " TEXT, " +
                DAOFavoritosDatabase.ID + " INTEGER PRIMARY KEY, " +
                DAOFavoritosDatabase.BACKDROP_PATH + " TEXT, " +
                DAOFavoritosDatabase.NAME + " TEXT, " +
                DAOFavoritosDatabase.FIRST_AIR_DATE + " TEXT, " +
                DAOFavoritosDatabase.TAGLINE + " TEXT, " +
                DAOFavoritosDatabase.NUMBER_OF_SEASONS + " INTEGER, " +
                DAOFavoritosDatabase.NUMBER_OF_EPISODES + " INTEGER, " +
                DAOFavoritosDatabase.BUDGET + " INTEGER, " +
                DAOFavoritosDatabase.REVENUE + " INTEGER )";
        db.execSQL(query3);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }







}
