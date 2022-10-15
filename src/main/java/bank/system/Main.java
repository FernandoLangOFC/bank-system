package bank.system;

import bank.system.domain.common.Status;
import bank.system.domain.user.User;
import bank.system.domain.user.UserAuth;
import bank.system.domain.user.UserIdentifier;
import bank.system.infrastructure.exception.OperationException;
import bank.system.infrastructure.persistence.PostgresConnection;
import bank.system.infrastructure.persistence.user.UserPostgresGateway;
import bank.system.infrastructure.repository.UserPostgresRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private UserPostgresRepository userPostgresRepository;
    private UserPostgresGateway userPostgresGateway;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        userPostgresRepository = new UserPostgresRepository(PostgresConnection.getConnection());
        userPostgresGateway = new UserPostgresGateway(userPostgresRepository);
        User user = User.create("fernando2", "45111222222");
        user.setDocumentNumber("9999999");
        user.setEmail("secure@email.com");
        user.setFullName("fernando pika das galaxiyyyiiiaaas");
        user.setPhone("99999992");
        Status<?> status = userPostgresRepository.createUser(user);
        System.out.println(status.parseAndGetBody(User.class).toString());
        userPostgresGateway.create(user);

        Label l = new Label("Hello, JavaFX .");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}