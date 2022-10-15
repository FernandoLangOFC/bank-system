package bank.system;

import bank.system.infrastructure.common.Status;
import bank.system.domain.user.User;
import bank.system.infrastructure.persistence.PostgresConnection;
import bank.system.infrastructure.persistence.user.UserPostgresGateway;
import bank.system.infrastructure.repository.UserPostgresRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private UserPostgresRepository userPostgresRepository;
    private UserPostgresGateway userPostgresGateway;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label l = new Label("Hello, JavaFX .");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}