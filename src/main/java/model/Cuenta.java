package model;

public class Cuenta {
    private String usuario;
    private String password;
    private Double balance;


    public Cuenta(String usuario, String password, Double balance) {
        this.usuario = usuario;
        this.password = password;
        this.balance = balance;
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
}
