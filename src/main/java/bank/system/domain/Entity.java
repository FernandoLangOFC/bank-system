package bank.system.domain;

import lombok.EqualsAndHashCode;

import java.util.Objects;

@EqualsAndHashCode
public abstract class Entity<E extends Identifier<?>> {
    protected final E id;

    protected Entity(final E anId) {
        Objects.requireNonNull(anId, " 'id' should not be null");
        this.id = anId;
    }
}