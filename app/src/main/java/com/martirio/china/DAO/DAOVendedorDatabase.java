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

public class DAOVendedorDatabase extends DatabaseHelper {

   public static final String NOMBREPRODUCTO = "NOMBREPRODUCTO";
   public static final String DESCRIPCION = "DESCRIPCION";
   public static final String PESOBULTO= "PESOBULTO";
   public static final String CANTIDADBULTO = "CANTIDADBULTO";
   public static final String PRECIOBULTO = "PRECIOBULTO";
   public static final String MONEDABULTO = "MONEDABULTO";
   public static final String ANCHOBULTO = "ANCHOBULTO";
   public static final String ALTOBULTO = "ALTOBULTO";
   public static final String PROFUNDIDADBULTO = "PROFUNDIDADBULTO";
   public static final String IDPRODUCTO = "IDPRODUCTO";
   public static final String PRECIOUNITARIO = "PRECIOUNITARIO";
   public static final String TABLE_VENDEDORES = "TABLE_VENDEDORES";





    public DAOVendedorDatabase(Context context) {
        super(context);
    }


    public void addFormato (Producto unProducto, String tipoFormato){

        if(!checkIfExist(unProducto.getIdProducto())) {

            SQLiteDatabase database = getWritableDatabase();

            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();
            row.put(TITLE, unProducto.getTitle());
            row.put(RELEASE_DATE, unProducto.getRelease_date());
            row.put(OVERVIEW, unProducto.getOverview());
            row.put(VOTE_AVERAGE, unProducto.getVote_average());
            row.put(TIPO_FORMATO,tipoFormato);
            row.put(POSTER_PATH, unProducto.getPoster_path());
            row.put(ID, unProducto.getId());
            row.put(BACKDROP_PATH, unProducto.getBackdrop_path());
            row.put(NAME, unProducto.getName());
            row.put(FIRST_AIR_DATE, unProducto.getFirst_air_date());
            row.put(TAGLINE, unProducto.getTagline());
            row.put(NUMBER_OF_SEASONS, unProducto.getNumber_of_seasons());
            row.put(NUMBER_OF_EPISODES, unProducto.getNumber_of_episodes());
            row.put(BUDGET, unProducto.getBudget());
            row.put(REVENUE, unProducto.getRevenue());
            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_FORMATOS, null, row);

            database.close();
        }
    }

    public void addFormatos(List<Producto> formatosList,String tipoFormato){

        for(Producto unProducto : formatosList){
            addFormato(unProducto, tipoFormato);
        }
    }


    public List<Producto> getAllFormatosPorTipo(String tipoFormato){

        List<Producto> formatos  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_FORMATOS+" WHERE TIPO_FORMATO = '"+tipoFormato+"'";

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));
            formatos.add(unProducto);
        }
        //CERRAR
        cursor.close();
        database.close();

        return formatos;
    }


    public List<Producto> getFormatosConFiltro(String queBuscoEnInet, String tipoFormato){
        String select;

        List<Producto> formatos  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        if (queBuscoEnInet==null ||queBuscoEnInet.startsWith("https://")) {
            select = "SELECT * FROM " + TABLE_FORMATOS;
        }
        else{
            select = "SELECT * FROM " + TABLE_FORMATOS+" WHERE NAME LIKE '%"+queBuscoEnInet+"%' AND TIPO_FORMATO LIKE "+"'%"+tipoFormato+"%'";
        }

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));
            formatos.add(unProducto);
        }

        //CERRAR
        cursor.close();
        database.close();

        return formatos;
    }





    public Producto getFormato(String idProducto){

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTOS +
                        " WHERE ID=" + idProducto;

        Cursor cursor = database.rawQuery(query, null);
        Producto unProducto = null;
        if(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO

            unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));
        }

        cursor.close();
        database.close();

        return unProducto;
    }

    public Boolean checkIfExist(String idProducto){
        Producto unProducto = getFormato(idProducto);
        return !(unProducto == null);
    }

    public List<Producto> busquedaPorVotoPeliculas(){
        String select;

        List<Producto> formatos  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();



        select = "SELECT * FROM " + TABLE_FORMATOS+" WHERE TITLE IS NOT NULL ORDER BY VOTE_AVERAGE ASC";



        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));

            formatos.add(unProducto);
        }

        //CERRAR
        cursor.close();
        database.close();

        return formatos;
    }

    public List<Producto> busquedaPorPresupuestoPeliculas(){
        String select;

        List<Producto> formatos  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();



        select = "SELECT * FROM " + TABLE_FORMATOS+" WHERE TITLE IS NOT NULL ORDER BY BUDGET ASC";



        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));

            formatos.add(unProducto);
        }

        //CERRAR
        cursor.close();
        database.close();

        return formatos;
    }


    public List<Producto> busquedaPorVotoSerie(){
        String select;

        List<Producto> formatos  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();



        select = "SELECT * FROM " + TABLE_FORMATOS+" WHERE NAME IS NOT NULL ORDER BY VOTE_AVERAGE ASC";



        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));

            formatos.add(unProducto);
        }

        //CERRAR
        cursor.close();
        database.close();

        return formatos;
    }

    public List<Producto> busquedaPorPresupuestoSerie(){
        String select;

        List<Producto> formatos  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();



        select = "SELECT * FROM " + TABLE_FORMATOS+" WHERE NAME IS NOT NULL ORDER BY BUDGET ASC";



        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Producto unProducto = new Producto();
            unProducto.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            unProducto.setRelease_date(cursor.getString(cursor.getColumnIndex(RELEASE_DATE)));
            unProducto.setOverview(cursor.getString(cursor.getColumnIndex(OVERVIEW)));
            unProducto.setVote_average(cursor.getFloat(cursor.getColumnIndex(VOTE_AVERAGE)));
            unProducto.setTipoFormato(cursor.getString(cursor.getColumnIndex(TIPO_FORMATO)));
            unProducto.setPoster_path(cursor.getString(cursor.getColumnIndex(POSTER_PATH)));
            unProducto.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            unProducto.setBackdrop_path(cursor.getString(cursor.getColumnIndex(BACKDROP_PATH)));
            unProducto.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            unProducto.setFirst_air_date(cursor.getString(cursor.getColumnIndex(FIRST_AIR_DATE)));
            unProducto.setTagline(cursor.getString(cursor.getColumnIndex(TAGLINE)));
            unProducto.setNumber_of_seasons(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_SEASONS)));
            unProducto.setNumber_of_episodes(cursor.getInt(cursor.getColumnIndex(NUMBER_OF_EPISODES)));
            unProducto.setBudget(cursor.getInt(cursor.getColumnIndex(BUDGET)));
            unProducto.setRevenue(cursor.getInt(cursor.getColumnIndex(REVENUE)));

            formatos.add(unProducto);
        }

        //CERRAR
        cursor.close();
        database.close();

        return formatos;
    }
}
