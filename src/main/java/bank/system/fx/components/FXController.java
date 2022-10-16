package bank.system.fx.components;

public abstract class FXController {

    public abstract void onLoad();

    public void initialize() {
        onLoad();
    }
}