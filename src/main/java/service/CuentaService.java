package service;

import model.Cuenta;

import java.util.List;

public interface CuentaService {
    Cuenta getByName(String name);

    List<Cuenta> getAll();

    void update(List<Cuenta> cuentas);
}
