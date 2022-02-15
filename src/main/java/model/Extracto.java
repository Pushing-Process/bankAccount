package model;

import java.util.Date;

public class Extracto {
    private double total;

    private double saldo;
    private Tipo tipo;

    public Extracto(double saldo, double total, Tipo tipo) {
        this.saldo = saldo;
        this.total = total;
        this.tipo = tipo;
        this.fecha = new Date();
    }

    private Date fecha;

    public double getTotal() {
        return total;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public enum Tipo {
        INGRESO, RETIRO, TRANSFERENCIA
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}