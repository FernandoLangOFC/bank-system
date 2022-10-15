package bank.system;

import bank.system.domain.common.Status;
import bank.system.domain.user.User;
import bank.system.domain.user.UserAuth;
import bank.system.domain.user.UserIdentifier;
import bank.system.infrastructure.persistence.PostgresConnection;
import bank.system.infrastructure.repository.UserPostgresRepository;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

  private UserPostgresRepository userPostgresRepository;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
      userPostgresRepository = new UserPostgresRepository(PostgresConnection.getConnection());
      Status<?> status = userPostgresRepository.findUserAuthPassword("DOCUMENT" ,"123456");

      System.out.println(status.parseAndGetBody(UserAuth.class).toString());


      Label l = new Label("Hello, JavaFX .");
      Scene scene = new Scene(new StackPane(l), 640, 480);
      stage.setScene(scene);
      stage.show();
  }
}