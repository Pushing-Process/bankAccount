package model;

import java.util.Date;

public class Extracto {
    public Extracto(double total, double saldo, Tipo tipo) {
        this.total = total;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    private double total;
    private double saldo;
    private Tipo tipo;

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public enum Tipo {
        INGRESO, RETIRO, TRANSFERENCIA
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}