package model;

import java.util.ArrayList;

public class Cuenta {
    private String usuario;
    private String password;
    private Double balance;
    private ArrayList<Extracto> extractos;

    public Cuenta(String usuario, String password, Double balance, ArrayList<Extracto> extractos) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
}
