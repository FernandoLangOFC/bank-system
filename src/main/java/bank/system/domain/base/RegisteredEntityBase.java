package bank.system.domain.base;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class RegisteredEntityBase<T> extends EntityBase<T> {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RegisteredEntityBase(T id) {
        super(id);
    }
}