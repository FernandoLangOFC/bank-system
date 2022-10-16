package bank.system.infrastructure.api.controller;

import bank.system.fx.components.FXController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController implements FXController {

    @FXML
    private Button btnTest;

    public void onClick() {
        btnTest.setText("Hello");
    }


    @Override
    public void initialize() {
        btnTest.setText("Click me to say hello");
    }
}