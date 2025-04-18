module co.edu.uniquindio.parking.parking_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.parking.parking_app to javafx.fxml;
    exports co.edu.uniquindio.parking.parking_app;
    exports co.edu.uniquindio.parking.parking_app.model;
    opens co.edu.uniquindio.parking.parking_app.model to javafx.fxml;

    opens co.edu.uniquindio.parking.parking_app.viewController;
    exports co.edu.uniquindio.parking.parking_app.viewController;
}