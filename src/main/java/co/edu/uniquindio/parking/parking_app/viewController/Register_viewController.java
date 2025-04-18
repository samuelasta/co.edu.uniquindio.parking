package co.edu.uniquindio.parking.parking_app.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parking.parking_app.controller.RegisterController;


import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parking.parking_app.factory.ModelFactory;
import co.edu.uniquindio.parking.parking_app.model.Cliente;
import co.edu.uniquindio.parking.parking_app.model.enums.TipoCliente;
import co.edu.uniquindio.parking.parking_app.model.enums.TipoVehiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Register_viewController {
    private ModelFactory modelFactory = ModelFactory.getInstance();
    RegisterController registerController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtDisabilityRegister;

    @FXML
    private TextField txtIdRegister;

    @FXML
    private TextField txtNameRegister;

    @FXML
    private TextField txtTypeCarRegister;

    @FXML
    private TextField txtTypeClientRegister;

    @FXML
    private VBox vboxsuperior;

    @FXML
    void close(ActionEvent event) {
        // Obtener el Stage desde el botón que disparó el evento
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerUser(ActionEvent event) throws IOException {
        if(validarFormulario()) {
            String id = txtIdRegister.getText();
            String name = txtNameRegister.getText();

            // Declara las variables fuera de los if
            TipoVehiculo tipoVehiculo = TipoVehiculo.VEHICULO; // valor por defecto
            boolean disability = false;
            TipoCliente tipoCliente = TipoCliente.REGULAR; // valor por defecto
            // Asigna según el texto

            String tipoVehiculoTexto = txtTypeCarRegister.getText().toUpperCase();
            if (tipoVehiculoTexto.equals("AMBULANCIA")) {
                tipoVehiculo = TipoVehiculo.AMBULANCIA;
            } else if (tipoVehiculoTexto.equals("POLICIA")) {
                tipoVehiculo = TipoVehiculo.POLICIA;
            } else if (tipoVehiculoTexto.equals("BOMBEROS")) {
                tipoVehiculo = TipoVehiculo.BOMBEROS;
            } else if (tipoVehiculoTexto.equals("VEHICULO")) {
                tipoVehiculo = TipoVehiculo.VEHICULO;
            } else if (tipoVehiculoTexto.equals("MOTO")) {
                tipoVehiculo = TipoVehiculo.MOTO;
            }

            // Valida discapacidad
            String disabilityText = txtDisabilityRegister.getText().toLowerCase();
            if (disabilityText.equals("yes")) {
                disability = true;
            }

            // Tipo cliente
            String tipoClienteText = txtTypeClientRegister.getText().toUpperCase();
            if (tipoClienteText.equals("VIP")) {
                tipoCliente = TipoCliente.VIP;
            }

            // Crea el cliente
            Cliente cliente = new Cliente(id, name, tipoVehiculo, disability, tipoCliente);
            if (!modelFactory.getParking().verificarPorId(cliente.getId())) {
                modelFactory.getParking().registrarCliente(cliente);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("successful registration");
                alert.setHeaderText(null);
                alert.showAndWait();
                //para pasar al login luego del registro
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parking/parking_app/login-view.fxml"));
                Parent root = loader.load();
                Login_viewController controller = loader.getController();

                Stage stage = (Stage) txtIdRegister.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("This user is already registered");
                alert.setHeaderText(null);
                alert.showAndWait();
                clear();
            }
        }
        }

    private void clear() {
        txtDisabilityRegister.clear();
        txtIdRegister.clear();
        txtNameRegister.clear();
        txtTypeCarRegister.clear();
        txtTypeClientRegister.clear();
    }


    private boolean validarFormulario() {
        return !txtDisabilityRegister.getText().isEmpty() && !txtIdRegister.getText().isEmpty()
                && !txtNameRegister.getText().isEmpty() && !txtTypeCarRegister.getText().isEmpty() && !txtTypeClientRegister.getText().isEmpty();
    }

    @FXML
    void initialize() {
        registerController = new RegisterController();
    }

    @FXML
    void parkCar(ActionEvent event) {

    }

    @FXML
    void goOutParking(ActionEvent event) {

    }

    @FXML
    void goToLogin(ActionEvent event) {

    }
}


