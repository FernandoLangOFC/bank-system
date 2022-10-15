package bank.system.application.entity2;

public interface AbstractHandler<I, O> {
    O execute(I in);
}