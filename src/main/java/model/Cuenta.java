package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable, Cloneable {
    private String usuario;
    private Integer password;
    private Double balance;
    private ArrayList<Extracto> extractos;
    private String encriptado;

    public Cuenta(String usuario, Integer password, Double balance, ArrayList<Extracto> extractos) {
        this.usuario = usuario;
        this.password = password;
        this.balance = balance;
        this.extractos = extractos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ArrayList<Extracto> getExtractos() {
        return extractos;
    }

    public void setExtractos(ArrayList<Extracto> extractos) {
        this.extractos = extractos;
    }

    public String getEncriptado() {
        return encriptado;
    }

    public void setEncriptado(String encriptado) {
        this.encriptado = encriptado;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "usuario='" + usuario + '\'' + ", password=" + encriptado + ", balance=" + balance + ", " +
                "extractos=" + extractos + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
