package model;

import java.io.Serializable;

public class Extracto implements Serializable {
    private Cuenta personaTransferencia;

    private double total;
    private double saldo;
    private Tipo tipo;

    public Extracto(double total, double saldo, Tipo tipo, Cuenta personaTransferencia) {
        this.total = total;
        this.saldo = saldo;
        this.tipo = tipo;
        this.personaTransferencia = personaTransferencia;
    }

    public Extracto(double total, double saldo, Tipo tipo) {
        this.total = total;
        this.saldo = saldo;
        this.tipo = tipo;
        this.personaTransferencia = null;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public Cuenta getPersonaTransferencia() {
        return personaTransferencia;
    }

    public void setPersonaTransferencia(Cuenta personaTransferencia) {
        this.personaTransferencia = personaTransferencia;
    }

    public enum Tipo {
        INGRESO, RETIRO, TRANSFERENCIA
    }

    @Override
    public String toString() {
        return "Extracto{" +
                "personaTransferencia=" + personaTransferencia +
                ", total=" + total +
                ", saldo=" + saldo +
                ", tipo=" + tipo +
                '}';
    }
}