package service;

import model.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentaServiceImpl implements CuentaService {

    protected static List<Cuenta> cuentas = new ArrayList<>();

    static {
        cuentas.add(new Cuenta("dani", "111", (double) 100, new ArrayList<>()));
        cuentas.add(new Cuenta("flavio", "111", (double) 110, new ArrayList<>()));
        cuentas.add(new Cuenta("oleh", "111", (double) 120, new ArrayList<>()));
    }

    @Override
    public Cuenta getByName(String name) {
        return cuentas.stream()
                .filter(cuenta -> name.equals(cuenta.getUsuario()))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Cuenta> getAll() {
        return cuentas;
    }

    @Override
    public void update(List<Cuenta> newCuentas) {
        cuentas = newCuentas;
    }
}
