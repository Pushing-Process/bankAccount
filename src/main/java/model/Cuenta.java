package model;

import java.util.ArrayList;

public class Cuenta {
    private String usuario;
    private Integer password;
    private Double balance;
    private ArrayList<Extracto> extractos;

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

    @Override
    public String toString() {
        return "Cuenta{" +
                "usuario='" + usuario + '\'' +
                ", password=" + password +
                ", balance=" + balance +
                ", extractos=" + extractos +
                '}';
    }
}
