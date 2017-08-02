package com.martirio.china.Modelo;

import java.util.List;

/**
 * Created by Martirio on 01/08/2017.
 */

public class Vendedor {
    private String nombreVendedor;
    private String mailVendedor;
    private String idVendedor;
    private String telefonoVendedor;
    private String fotoVendedor;
    private String observacionVendedor;
    private List<Producto> listaProductosVendedor;

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

    public List<Producto> getListaProductosVendedor() {
        return listaProductosVendedor;
    }

    public void setListaProductosVendedor(List<Producto> listaProductosVendedor) {
        this.listaProductosVendedor = listaProductosVendedor;
    }
}
