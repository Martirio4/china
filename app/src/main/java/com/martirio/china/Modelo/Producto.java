package com.martirio.china.Modelo;

import java.util.List;

/**
 * Created by Martirio on 01/08/2017.
 */

public class Producto {
    private String nombreProducto;
    private List<String> fotos;
    private String descripcion;
    private Integer pesoBulto;
    private Integer cantidadBulto;
    private Double precioBulto;
    private String monedaBulto;
    private Integer anchoBulto;
    private Integer altoBulto;
    private String idProducto;
    private String idVendedor;
    private Double precioUnitario;
    private Integer profundidadBulto;

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Producto() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPesoBulto() {
        return pesoBulto;
    }

    public void setPesoBulto(Integer pesoBulto) {
        this.pesoBulto = pesoBulto;
    }

    public Integer getCantidadBulto() {
        return cantidadBulto;
    }

    public void setCantidadBulto(Integer cantidadBulto) {
        this.cantidadBulto = cantidadBulto;
    }

    public Double getPrecioBulto() {
        return precioBulto;
    }

    public void setPrecioBulto(Double precioBulto) {
        this.precioBulto = precioBulto;
    }

    public String getMonedaBulto() {
        return monedaBulto;
    }

    public void setMonedaBulto(String monedaBulto) {
        this.monedaBulto = monedaBulto;
    }

    public Integer getAnchoBulto() {
        return anchoBulto;
    }

    public void setAnchoBulto(Integer anchoBulto) {
        this.anchoBulto = anchoBulto;
    }

    public Integer getAltoBulto() {
        return altoBulto;
    }

    public void setAltoBulto(Integer altoBulto) {
        this.altoBulto = altoBulto;
    }

    public Integer getProfundidadBulto() {
        return profundidadBulto;
    }

    public void setProfundidadBulto(Integer profundidadBulto) {
        this.profundidadBulto = profundidadBulto;
    }
}
