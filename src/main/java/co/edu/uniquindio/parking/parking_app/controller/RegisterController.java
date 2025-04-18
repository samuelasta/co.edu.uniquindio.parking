package co.edu.uniquindio.parking.parking_app.controller;

import co.edu.uniquindio.parking.parking_app.factory.ModelFactory;

public class RegisterController {
    ModelFactory modelFactory;
    public RegisterController() {
        modelFactory = ModelFactory.getInstance(); // Si usas el patrón Singleton
    }
}
