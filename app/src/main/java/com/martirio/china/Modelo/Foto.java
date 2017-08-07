package com.martirio.china.Modelo;

import io.realm.RealmObject;

/**
 * Created by elmar on 5/8/2017.
 */

public class Foto extends RealmObject{
private String foto;

    public Foto() {
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
