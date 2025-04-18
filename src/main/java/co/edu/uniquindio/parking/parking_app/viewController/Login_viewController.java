package co.edu.uniquindio.parking.parking_app.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.parking.parking_app.controller.LoginController;
import co.edu.uniquindio.parking.parking_app.controller.ParkingController;
import co.edu.uniquindio.parking.parking_app.factory.ModelFactory;
import co.edu.uniquindio.parking.parking_app.model.Cliente;
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

public class Login_viewController {

   LoginController loginController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtidcliente;

    @FXML
    private TextField txtnamecliente;

    @FXML
    private VBox vboxIzquierdo;

    @FXML
    void onLoginCliente(ActionEvent event) {
        logIn(event);
    }

    private void logIn(ActionEvent event) {
        if (!validarFormulario()) {
            return;
        }
        try{
            String nombre = txtnamecliente.getText();
            String id = txtidcliente.getText();
            Cliente clienteValidado = loginController.validarAcceso(id, nombre);
            if(clienteValidado == null){
                mostrarMensaje("Acceso denegado", "credenciales invalidas", "nombre o contraseña incorrectos", Alert.AlertType.WARNING);
                return;
            }
            else{
                loginController.guardarSesion(clienteValidado);
                irAlParking(clienteValidado, event);
            }

        } catch (Exception e) {
            mostrarMensaje("error de redireccion", "acceso denegado", "credenciales invalidas", Alert.AlertType.ERROR);
            return;
        }
    }

    public void mostrarMensaje(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();

    }

    private void irAlParking(Cliente clienteValidado, ActionEvent event) {
        try{
            if(clienteValidado != null){
                browseWindowNormalSize("/co/edu/uniquindio/parking/parking_app/parking-view.fxml", "Parking", event);
            }
            
        } catch (Exception e) {
            mostrarMensaje("error de redireccion", "No se pudo cargar la siguiente ventana", "ocurrio un error inesperado", Alert.AlertType.ERROR);
        }
    }

    public void browseWindowNormalSize(String nameFileFxml, String titleWindow, ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nameFileFxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(titleWindow);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean validarFormulario() {
        return !txtidcliente.getText().isEmpty() && !txtnamecliente.getText().isEmpty();
    }

    @FXML
    void onRegisterCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/parking/parking_app/register-view.fxml"));
            Parent root = loader.load();
            Register_viewController controller = loader.getController();

            // Obtener el stage actual desde el botón u otro componente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

        loginController = new LoginController();

    }

}

