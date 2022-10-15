package bank.system.domain.user;

public record UserAuth<T> (T id, String password) {
}