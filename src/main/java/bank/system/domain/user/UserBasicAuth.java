package bank.system.domain.user;

public record UserBasicAuth(String username, String password) {
    public static UserBasicAuth from(final String username, final String password) {
        return new UserBasicAuth(username, password);
    }
}