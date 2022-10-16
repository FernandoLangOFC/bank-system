package bank.system.infrastructure.api.controller;

import bank.system.fx.components.FXController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class LoginController extends FXController {

    public Button btnTest;
    private static Integer count = 0;

    public void onClick(ActionEvent actionEvent) {
        btnTest.setText(count.toString());
        count++;
        System.out.println("Hello fx");
    }

    @Override
    public void onLoad() {
        btnTest.setText(count.toString());
    }
}