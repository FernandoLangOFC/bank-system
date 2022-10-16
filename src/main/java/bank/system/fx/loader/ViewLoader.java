package bank.system.fx.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewLoader.class);
    private static Map<View, Parent> views;

    public static void loadViews() {
        views = new EnumMap<>(View.class);

        LOGGER.info("Initializing views... ");
        Arrays.stream(View.values()).forEach(ViewLoader::loadView);
    }

    public static void loadView(View view) {
        try {
            views.put(view, new FXMLLoader(view.getUrl()).load());
            LOGGER.info("Loaded: {} - {} ", view.name(), view.getUrl().getFile());
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static Parent getParent(View view) {
        return views.get(view);
    }
}