package bank.system;

import bank.system.fx.FXConfiguration;
import bank.system.fx.loader.View;
import bank.system.fx.loader.ViewLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXConfiguration.loadConfiguration(stage);

        Scene scene = new Scene(new StackPane(ViewLoader.getParent(View.LOGIN_PAGE)));
        stage.setScene(scene);
        stage.show();

        FXConfiguration.afterShow(stage);
    }
}