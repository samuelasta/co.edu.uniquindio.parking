package co.edu.uniquindio.parking.parking_app.controller;

import co.edu.uniquindio.parking.parking_app.factory.ModelFactory;
import co.edu.uniquindio.parking.parking_app.model.Cliente;
import co.edu.uniquindio.parking.parking_app.model.Parking;
import co.edu.uniquindio.parking.parking_app.model.Puesto;

import java.util.List;
import java.util.PriorityQueue;

public class ParkingController {
    public ModelFactory modelFactory;
    public ParkingController() {
        modelFactory = ModelFactory.getInstance(); // Si usas el patrón Singleton

    }
    public void estacionar(Cliente cliente) {
        // se accede al objeto Parking que está dentro de modelFactory
        Parking parking = modelFactory.getParking();
        parking.estacionar(cliente);
    }
    public List<Cliente> getListaEnCola() {
        return modelFactory.getCola();
    }

    public List<Parking> getListaEnPuesto() {
        return (List<Parking>) modelFactory.getParking();
    }
    public Puesto[] getPuestos() {
        return modelFactory.getParking().getPuestos(); // suponiendo que el parking es tu instancia de Parking
    }

    public PriorityQueue<Cliente> getColaClientes() {
        return modelFactory.getParking().getColaPrioridad();
    }

    public void salirParqueadero(Cliente cliente) {
        Parking parking = modelFactory.getParking();
        parking.salirParqueadero(cliente);
    }
}
