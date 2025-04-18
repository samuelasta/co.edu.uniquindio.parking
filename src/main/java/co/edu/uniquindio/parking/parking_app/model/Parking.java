package co.edu.uniquindio.parking.parking_app.model;

import co.edu.uniquindio.parking.parking_app.model.enums.TipoCliente;
import co.edu.uniquindio.parking.parking_app.model.enums.TipoVehiculo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Parking {
    private Puesto[] puestos;
    private PriorityQueue<Cliente> colaPrioridad;
    private HashSet<Cliente> clientesRegistrados;

    public Parking(int capacidad) {
        this.puestos = new Puesto[capacidad];
        for (int i = 0; i < capacidad; i++) {
            puestos[i] = new Puesto(); // E   puestos[i] = mpieza desde el puesto 1
        }
        colaPrioridad = new PriorityQueue<>();
        clientesRegistrados = new HashSet<>();
    }

    //cuando un cliente llega a parquear
    public void estacionar(Cliente cliente) {
        try {
            colaPrioridad.add(cliente);
            cliente.setTiempoLlegada(System.currentTimeMillis());
            for (int i = 0; i < puestos.length; i++) {
                if (puestos[i].estaDisponible()) {
                    Cliente primeroEnCola = colaPrioridad.poll();
                    puestos[i].asignarCliente(primeroEnCola);
                    System.out.println("puesto asignado al cliente: " + primeroEnCola.getNombre() + " " + primeroEnCola.getId());
                    return;
                }
            }
            System.out.println("El parqueadero se encuentra lleno, quedaste en lista de espera " + cliente.getNombre());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //cuando un cliente sale de el arreglo de puestos, verifica si hay alguno en cola y lo agrega
    public void salirParqueadero(Cliente cliente) {
        try {
            for (int i = 0; i < puestos.length; i++) {
                if (puestos[i].getCliente().equals(cliente)) {
                    puestos[i].liberar();
                    System.out.println("Cliente: " + cliente.getNombre() + " ha salido del parqueadero.");
                    break;
                }
            }
            if (!colaPrioridad.isEmpty()) {
                for (Puesto puesto : puestos) {
                    if (puesto.estaDisponible()) {
                        Cliente primeroEnCola = colaPrioridad.poll();
                        puesto.asignarCliente(primeroEnCola);
                        System.out.println("Cliente: " + primeroEnCola.getNombre() + " " + primeroEnCola.getId() + " ha ingresado al parqueadero.");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // cuando lo agregue a la cola: cliente.setTiempoLlegada(System.currentTimeMillis());

    //Registra un cliente, con el hashset nos aseguramos de que no haya repetidos
    public void registrarCliente(Cliente cliente) {
        try {
            clientesRegistrados.add(cliente);
            System.out.println("Cliente registrado: " + cliente.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //imprime la lista de clientes registrados
    public void mostrarClientesRegistrados() {
        try {
            for (Cliente c : clientesRegistrados) {
                System.out.println(c.getId() + " " + c.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //elimina de la lista de registrados por id
    public void eliminarRegistrado(String id) {
        Iterator<Cliente> it = clientesRegistrados.iterator();
        while (it.hasNext()) {
            Cliente c = it.next();
            if (c.getId().equals(id)) {
                it.remove();
                return;
            }
        }
        System.out.println("El cliente no se encuentra registrado");
    }

    //retorna un cliente buscado por id
    public Cliente buscarPorId(String id) {
        for (Cliente cliente: clientesRegistrados) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }
    //retorna un cliente buscado por id
    public boolean verificarPorId(String id) {
        for (Cliente cliente: clientesRegistrados) {
            if (cliente.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    //actualiza la informacion del cliente
    public boolean actualizarCliente(String id, String nombre, TipoVehiculo nuevoTipo, boolean nuevaCondicion, TipoCliente nuevoTipoCliente) {
        Cliente actualizado = buscarPorId(id);
        if (actualizado != null) {
            actualizado.setNombre(nombre);
            actualizado.setTipo(nuevoTipo);
            actualizado.setCondicion(nuevaCondicion);
            actualizado.setTipoCliente(nuevoTipoCliente);
            System.out.println("Cliente actualizado: " + actualizado.getNombre());
            return true;
        }
        System.out.println("Cliente no encontrado");
        return false;
    }
    public void mostrarEnCola() {
        try {
            PriorityQueue<Cliente> copia = new PriorityQueue<>(colaPrioridad);
            while (!copia.isEmpty()) {
                System.out.println("En lista de espera: " + copia.poll().getNombre());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void limpiarRegistros() {
        clientesRegistrados.clear();
    }

    public Puesto[] getPuestos() {
        return puestos;
    }

    public Parking setPuestos(Puesto[] puestos) {
        this.puestos = puestos;
        return this;
    }

    public PriorityQueue<Cliente> getColaPrioridad() {
        return colaPrioridad;
    }

    public Parking setColaPrioridad(PriorityQueue<Cliente> colaPrioridad) {
        this.colaPrioridad = colaPrioridad;
        return this;
    }

    public HashSet<Cliente> getClientesRegistrados() {
        return clientesRegistrados;
    }

    public Parking setClientesRegistrados(HashSet<Cliente> clientesRegistrados) {
        this.clientesRegistrados = clientesRegistrados;
        return this;
    }

    public Cliente validarAcceso(String id, String nombre) {
        for(Cliente cliente: clientesRegistrados) {
            if(verificarPorId(id)){
                if(cliente.getNombre().equals(nombre)) {
                    return cliente;
                }
            }
        }
        return null;
    }

}
