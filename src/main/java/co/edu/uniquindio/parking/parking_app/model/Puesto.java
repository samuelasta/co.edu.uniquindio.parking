package co.edu.uniquindio.parking.parking_app.model;

public class Puesto {
    private Cliente cliente; // null si está vacío

    public Puesto(Cliente cliente) {
        this.cliente = cliente;
    }

    public Puesto() {
        this.cliente = null;
    }

    public boolean estaDisponible() {
        return cliente == null;
    }

    public void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void liberar() {
        this.cliente = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Puesto setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }
}
