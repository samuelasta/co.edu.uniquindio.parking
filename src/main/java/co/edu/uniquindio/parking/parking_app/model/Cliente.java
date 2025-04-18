package co.edu.uniquindio.parking.parking_app.model;

import co.edu.uniquindio.parking.parking_app.model.enums.TipoCliente;
import co.edu.uniquindio.parking.parking_app.model.enums.TipoVehiculo;

public class Cliente implements Comparable<Cliente> {
    private String id;
    private String nombre;
    private TipoVehiculo tipo;
    private boolean condicion;
    private TipoCliente tipoCliente;
    private long tiempoLlegada;


    public Cliente(String id, String nombre, TipoVehiculo tipo, boolean condicion, TipoCliente tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.condicion = condicion;
        this.tipoCliente = tipoCliente;
    }

    @Override
    public int compareTo(Cliente cliente) {
        int mp = Integer.compare(cliente.calcularPrioridad(), this.calcularPrioridad());
        if (mp == 0) {
            return Long.compare(cliente.tiempoLlegada, this.tiempoLlegada); // más antiguo primero
        }
        return mp;
    }

    // calcula la prioridad ;)
    public int calcularPrioridad(){
        int prioridad = 0;
        if(tipo == TipoVehiculo.POLICIA || tipo == TipoVehiculo.AMBULANCIA || tipo == TipoVehiculo.BOMBEROS){
            prioridad += 3;
        }
        if(condicion){
            prioridad += 2;
        }
        if(tipoCliente == TipoCliente.VIP){
            prioridad += 1;
        }
        return prioridad;
    }

    public long getTiempoLlegada() {
        return tiempoLlegada;
    }

    public Cliente setTiempoLlegada(long tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
        return this;
    }

    public String getId() {
        return id;
    }

    public Cliente setId(String id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Cliente setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public Cliente setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
        return this;
    }

    public boolean isCondicion() {
        return condicion;
    }

    public Cliente setCondicion(boolean condicion) {
        this.condicion = condicion;
        return this;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public Cliente setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
        return this;
    }


    public String getTipoVehiculo() {
        return tipo.name().toLowerCase();
    }
}
