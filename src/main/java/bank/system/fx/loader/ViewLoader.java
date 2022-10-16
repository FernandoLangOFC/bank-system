package bank.system.fx.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewLoader.class);
    private static Map<View, Parent> VIEWS_LOADER;

    public static void loadViews() {
        VIEWS_LOADER = new HashMap<>();

        LOGGER.info("Initializing views... ");
        Arrays.stream(View.values()).forEach(e -> {
            try {
                VIEWS_LOADER.put(e, new FXMLLoader(e.getUrl()).load());
                LOGGER.info("Loaded: {} - {} ", e.name(), e.getUrl().getFile());
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }
        });
    }

    public static Parent getParent(View view) {
        return VIEWS_LOADER.get(view);
    }
}