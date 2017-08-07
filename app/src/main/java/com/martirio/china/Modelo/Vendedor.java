package com.martirio.china.Modelo;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Martirio on 01/08/2017.
 */

public class Vendedor extends RealmObject {
    private String nombreVendedor;
    private String mailVendedor;

    private String idVendedor;
    private String telefonoVendedor;
    private String fotoVendedor;
    private String observacionVendedor;
    private RealmList<Producto> listaProductosVendedor;

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getFotoVendedor() {
        return fotoVendedor;
    }

    public void setFotoVendedor(String fotoVendedor) {
        this.fotoVendedor = fotoVendedor;
    }

    public Vendedor() {
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getMailVendedor() {
        return mailVendedor;
    }

    public void setMailVendedor(String mailVendedor) {
        this.mailVendedor = mailVendedor;
    }

    public String getTelefonoVendedor() {
        return telefonoVendedor;
    }

    public void setTelefonoVendedor(String telefonoVendedor) {
        this.telefonoVendedor = telefonoVendedor;
    }

    public String getObservacionVendedor() {
        return observacionVendedor;
    }

    public void setObservacionVendedor(String observacionVendedor) {
        this.observacionVendedor = observacionVendedor;
    }

    public RealmList<Producto> getListaProductosVendedor() {
        return listaProductosVendedor;
    }

    public void setListaProductosVendedor(RealmList<Producto> listaProductosVendedor) {
        this.listaProductosVendedor = listaProductosVendedor;
    }
}
