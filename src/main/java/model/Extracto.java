package model;

import java.util.Date;

public class Extracto {
    // Si el ingreso es negativo, se trata de una extraccion.
    private boolean ingreso;
    private double saldo;
    private Date fecha;

    public Extracto(double saldo) {
        this.ingreso = saldo > 0;
        this.saldo = saldo;
        this.fecha = new Date();
    }

    public boolean isIngreso() {
        return ingreso;
    }

    public void setIngreso(boolean ingreso) {
        this.ingreso = ingreso;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Extracto{" +
                "ingreso=" + ingreso +
                ", saldo=" + saldo +
                ", fecha=" + fecha +
                '}';
    }
}
