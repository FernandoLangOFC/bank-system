package bank.system.domain.base;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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