package bank.system.fx;

import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.*;

import static bank.system.fx.loader.ViewLoader.loadViews;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FXConfiguration {
    private static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCALE_X = 0.6;
    private static final double SCALE_Y = 0.8;
    private static final double WIDTH = DIMENSION.width * SCALE_X;
    private static final double HEIGHT = DIMENSION.height * SCALE_Y;

    public static void loadConfiguration(Stage stage) {
        loadViews();

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
    }

    public static void afterShow(Stage stage) {
        stage.centerOnScreen();
    }
}