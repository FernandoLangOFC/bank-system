package bank.system.fx.loader;

import lombok.AllArgsConstructor;

import java.net.URL;

import static java.lang.String.format;

@AllArgsConstructor
public enum View {
    LOGIN_PAGE(View.class.getResource(viewName("login")));

    private final URL url;
    public URL getUrl() {
        return url;
    }
    private static String viewName(String name) {
        return format("/views/%s.bs.fxml", name);
    }
}