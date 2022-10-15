package bank.system.application.entity2;

public abstract class AbstractHandler<IN, OUT> {
    public abstract OUT execute(IN in);
}