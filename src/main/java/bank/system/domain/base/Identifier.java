package bank.system.domain.base;

public abstract class Identifier<E> {
    
    protected final E value;

    protected Identifier(final E aValue) {
        this.value = aValue;
    }

    public E getValue() {
        return this.value;
    }
}