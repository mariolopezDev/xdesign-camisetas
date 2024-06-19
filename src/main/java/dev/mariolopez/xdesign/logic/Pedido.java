/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.mariolopez.xdesign.Logic;

/**
 *
 * @author mariolopez
 */
public class Pedido {
    private int idPedido;
    private String tipoCamiseta;
    private String talla;
    private int cantidad; 
    private String codigoDiseno; 
    private String tipoPedido; 
    private String direccionEntrega; 
    private String formaPago; 
    private int numeroTelefonicoCliente; 
    private String nombreCompletoCliente; 

    public Pedido(int idPedido, String tipoCamiseta, String talla, int cantidad, String codigoDiseno, String tipoPedido, String direccionEntrega, String formaPago, int numeroTelefonicoCliente, String nombreCompletoCliente) {
        this.idPedido = idPedido;
        this.tipoCamiseta = tipoCamiseta;
        this.talla = talla;
        this.cantidad = cantidad;
        this.codigoDiseno = codigoDiseno;
        this.tipoPedido = tipoPedido;
        this.direccionEntrega = direccionEntrega;
        this.formaPago = formaPago;
        this.numeroTelefonicoCliente = numeroTelefonicoCliente;
        this.nombreCompletoCliente = nombreCompletoCliente;
    }
    
    public void actualizarDatos(String tipoCamiseta, String talla, int cantidad, String codigoDiseno, String tipoPedido, String direccionEntrega, String formaPago, int telefono, String nombreCliente) {
        this.tipoCamiseta = tipoCamiseta;
        this.talla = talla;
        this.cantidad = cantidad;
        this.codigoDiseno = codigoDiseno;
        this.tipoPedido = tipoPedido;
        this.direccionEntrega = direccionEntrega;
        this.formaPago = formaPago;
        this.numeroTelefonicoCliente = telefono;
        this.nombreCompletoCliente = nombreCliente;
    }

    public int getId() {
        return idPedido;
    }

    public void setId(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getTipoCamiseta() {
        return tipoCamiseta;
    }

    public void setTipoCamiseta(String tipoCamiseta) {
        this.tipoCamiseta = tipoCamiseta;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoDiseno() {
        return codigoDiseno;
    }

    public void setCodigoDiseno(String codigoDiseno) {
        this.codigoDiseno = codigoDiseno;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getNumeroTelefonicoCliente() {
        return numeroTelefonicoCliente;
    }

    public void setNumeroTelefonicoCliente(int numeroTelefonicoCliente) {
        this.numeroTelefonicoCliente = numeroTelefonicoCliente;
    }

    public String getNombreCompletoCliente() {
        return nombreCompletoCliente;
    }

    public void setNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
    }

    
    // Método recursivo para convertir cantidad a binario
    public String cantidadABinario(int cantidad) {
        if (cantidad == 0) {
            return "";
        } else {
            return cantidadABinario(cantidad / 2) + (cantidad % 2);
        }
    }
    
    @Override
    public String toString() {
        return "Pedido{" +
               "idPedido=" + idPedido +
               ", tipoCamiseta='" + tipoCamiseta + '\'' +
               ", talla='" + talla + '\'' +
               ", cantidad=" + cantidad +
               ", codigoDiseno='" + codigoDiseno + '\'' +
               ", tipoPedido='" + tipoPedido + '\'' +
               ", direccionEntrega='" + direccionEntrega + '\'' +
               ", formaPago='" + formaPago + '\'' +
               ", numeroTelefonicoCliente=" + numeroTelefonicoCliente +
               ", nombreCompletoCliente='" + nombreCompletoCliente + '\'' +
               '}';
    }

}
