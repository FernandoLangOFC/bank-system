package bank.system.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthRequest<T> {
    private T id;
    private String unHashedPassword;
    private String hashedPassword;
    private String authType;
    private String login;
}