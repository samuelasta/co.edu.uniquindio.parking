package co.edu.uniquindio.parking.parking_app.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parking.parking_app.controller.ParkingController;
import co.edu.uniquindio.parking.parking_app.model.Cliente;
import co.edu.uniquindio.parking.parking_app.model.Parking;
import co.edu.uniquindio.parking.parking_app.model.Puesto;
import co.edu.uniquindio.parking.parking_app.sesion.Sesion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Parking_viewController  {
    ParkingController parkingController;
    Cliente clienteOnSesion;

    private ObservableList<Cliente> clientesEnPuesto = FXCollections.observableArrayList();
    private ObservableList<Cliente> clientesEnCola = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Cliente, String> colCarroCola;

    @FXML
    private TableColumn<Cliente, String> colCarroPuesto;

    @FXML
    private TableColumn<Cliente, String> colIdCola;

    @FXML
    private TableColumn<Cliente, String> colIdPuesto;

    @FXML
    private TableColumn<Cliente, String> colNombreCola;

    @FXML
    private TableColumn<Cliente, String> colNombrePuesto;

    @FXML
    private TableView<Cliente> tablaClientesEnCola;

    @FXML
    private TableView<Cliente> tablaClientesEnPuesto;

    @FXML
    private VBox vboxsuperior;

    @FXML
    void close(ActionEvent event) {
        // Obtener el Stage desde el botón que disparó el evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void goOutParking(ActionEvent event) {
        Cliente cliente = clienteOnSesion; // cliente actual
        parkingController.salirParqueadero(cliente);
        actualizarTablas();
    }


    @FXML
    void parkCar(ActionEvent event) {
        Cliente cliente = clienteOnSesion;
        parkingController.estacionar(cliente);
        actualizarTablas();
    }

    private void actualizarTablas() {
        tablaClientesEnCola.setItems(obtenerClientesEnCola());
        tablaClientesEnPuesto.setItems(obtenerClientesEstacionados());
    }

    @FXML
    void initialize() {
        parkingController = new ParkingController();
        clienteOnSesion = (Cliente) Sesion.getInstance().getCliente();
        System.out.println("clienteOnSesion: " + clienteOnSesion.getNombre());
        initView();
    }

    private void initView() {
        initDataBinding();
        actualizarTablas(); // carga las listas en la vista con lo que tienen

    }
    private ObservableList<Cliente> obtenerClientesEstacionados() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        for (Puesto puesto : parkingController.getPuestos()) {
            if (!puesto.estaDisponible() && puesto.getCliente() != null) {
                clientes.add(puesto.getCliente());
            }
        }
        return clientes;
    }


    private ObservableList<Cliente> obtenerClientesEnCola() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        clientes.addAll(parkingController.getColaClientes()); // Asumiendo que tienes un getter para la PriorityQueue
        return clientes;
    }


    private void initDataBinding() {
        // Columnas de puestos
        colIdPuesto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        colNombrePuesto.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre()));
        colCarroPuesto.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoVehiculo().toString()));

        // Columnas de cola
        colIdCola.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        colNombreCola.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre()));
        colCarroCola.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoVehiculo().toString()));
    }

    public void setCliente(Cliente cliente) {
    }
}


