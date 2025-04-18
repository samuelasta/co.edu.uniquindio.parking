package co.edu.uniquindio.parking.parking_app.factory;

import co.edu.uniquindio.parking.parking_app.model.Cliente;
import co.edu.uniquindio.parking.parking_app.model.Parking;
import co.edu.uniquindio.parking.parking_app.model.enums.TipoCliente;
import co.edu.uniquindio.parking.parking_app.model.enums.TipoVehiculo;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Parking parking;

    public ModelFactory() {
        parking = new Parking(1);
        initializeData();
    }

    public static ModelFactory getModelFactory() {
        return getInstance();
    }

    public Parking getParking() {
        return parking;
    }

    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    public void initializeData() {
        Cliente cliente1 = new Cliente("1", "sam", TipoVehiculo.AMBULANCIA, false, TipoCliente.REGULAR );
        Cliente cliente2 = new Cliente("2", "miguel", TipoVehiculo.MOTO, false, TipoCliente.VIP );
        parking.getClientesRegistrados().add(cliente1);
        parking.getClientesRegistrados().add(cliente2);

    }

    public List<Cliente> getCola() {
        return  new ArrayList<>(parking.getColaPrioridad());
    }

    public Cliente validarAcceso(String id, String nombre) {
        return parking.validarAcceso(id, nombre);
    }
}
