package com.martirio.china.Modelo;

import java.util.List;

/**
 * Created by Martirio on 01/08/2017.
 */

public class Vendedor {
    private String nombre;
    private String mail;
    private String telefono;
    private String observacion;
    private List<Producto> listaProductosVendedor;

    public Vendedor() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<Producto> getListaProductosVendedor() {
        return listaProductosVendedor;
    }

    public void setListaProductosVendedor(List<Producto> listaProductosVendedor) {
        this.listaProductosVendedor = listaProductosVendedor;
    }
}
