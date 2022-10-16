package bank.system.infrastructure.api.controller;

import bank.system.fx.components.FXController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class LoginController extends FXController {

    @FXML
    private Button btnTest;
    private static Integer count = 0;

    public void onClick() {
        btnTest.setText(count.toString());
        count++;
    }

    @Override
    public void onLoad() {
        btnTest.setText(count.toString());
    }
}