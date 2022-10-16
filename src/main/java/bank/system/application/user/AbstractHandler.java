package bank.system.application.user;

public interface AbstractHandler<I, O> {
    O execute(I in);
}