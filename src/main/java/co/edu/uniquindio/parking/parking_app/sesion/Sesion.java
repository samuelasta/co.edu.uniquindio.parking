package co.edu.uniquindio.parking.parking_app.sesion;

import co.edu.uniquindio.parking.parking_app.model.Cliente;

public class Sesion {
    private Cliente cliente;
    private static Sesion INSTANCE;

    private Sesion(){
    }
    public static Sesion getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Sesion();
        }
        return INSTANCE;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void closeSesion(){
        cliente = null;
    }
}
