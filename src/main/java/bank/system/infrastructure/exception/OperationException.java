package bank.system.infrastructure.exception;

public class OperationException extends RuntimeException {
    public OperationException (final String message) {
        super(message);
    }
}