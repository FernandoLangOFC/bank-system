package bank.system.domain;

import lombok.EqualsAndHashCode;

import java.util.Objects;


@EqualsAndHashCode
public abstract class Entity<E extends Identifier<?>> {
    protected E id;

    // ID IS AUTO-GENERATED IN DATABASE
    protected Entity(final E anId) {
        Objects.requireNonNull(anId, " 'id' should not be null");
        this.id = anId;
    }

    protected Entity() {}
}