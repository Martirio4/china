package com.martirio.china.Modelo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by elmar on 5/8/2017.
 */

public class Orden extends RealmObject {

    private Vendedor vendedor;
    private Producto producto;

    @PrimaryKey
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Orden() {
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return producto.getNombreProducto()+" - "+vendedor.getNombreVendedor();
    }
}
