package bank.system.domain.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class RegisteredEntityBase<T> extends EntityBase<T> {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}