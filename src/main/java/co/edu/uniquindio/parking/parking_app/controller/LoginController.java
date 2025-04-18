package co.edu.uniquindio.parking.parking_app.controller;

import co.edu.uniquindio.parking.parking_app.factory.ModelFactory;
import co.edu.uniquindio.parking.parking_app.model.Cliente;
import co.edu.uniquindio.parking.parking_app.sesion.Sesion;

public class LoginController {
    ModelFactory modelFactory;

    public LoginController(){
        modelFactory = ModelFactory.getInstance();
    }

    public Cliente validarAcceso(String id, String nombre) {
        return modelFactory.validarAcceso(id, nombre);
    }

    public void guardarSesion(Cliente clienteValidado) {
        Sesion.getInstance().setCliente(clienteValidado);
    }
}
