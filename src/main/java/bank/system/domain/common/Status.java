package bank.system.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Data
@AllArgsConstructor
public class Status<E> {
    private String status;
    private LocalDateTime dateTime;
    private E body;

    public Status(final String staus, final E body) {
        this.dateTime = LocalDateTime.now();
        this.status = staus;
        this.body = body;
    }

    public Status(final String staus) {
        this.dateTime = LocalDateTime.now();
        this.status = staus;
        this.body = null;
    }

    @SuppressWarnings("unchecked")
    public <T> T parseAndGetBody(Class<T> _class) {
        if (_class.isAssignableFrom(body.getClass())) {
            return (T) body;
        }
        throw new RuntimeException(format("Can't parse %s to %s ", body.getClass().getSimpleName(), _class.getSimpleName()));
    }


    public enum Type {
        SUCCESS,
        ERROR
    }

}