package bank.system.infrastructure.common;

import bank.system.infrastructure.exception.OperationException;
import bank.system.utils.Formater;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

@Data
@AllArgsConstructor
public class Status<E> {
    private String situation;
    private LocalDateTime dateTime;
    private String message;
    private E body;

    public Status(final String situation, final E body) {
        this.dateTime = LocalDateTime.now();
        this.situation = situation;
        this.body = body;
    }

    public Status(final String staus, final String message) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
        this.situation = staus;
    }

    public Status(final String staus, final String message, final E body) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
        this.situation = staus;
        this.body = body;
    }

    public Status(final String staus) {
        this.dateTime = LocalDateTime.now();
        this.situation = staus;
        this.body = null;
    }

    public void resign(Status<E> newStatus) {
        this.message = newStatus.getMessage();
        this.dateTime = LocalDateTime.now();
        this.situation = newStatus.getSituation();
        this.body = newStatus.getBody();
    }

    public void clearBody() {
        this.body = null;
    }

    @SuppressWarnings("unchecked")
    public <T> T parseAndGetBody(Class<T> _class) {
        if (_class.isAssignableFrom(body.getClass())) {
            return (T) body;
        }
        throw new OperationException(format("Can't parse %s to %s ", body.getClass().getSimpleName(), _class.getSimpleName()));
    }

    @Override
    public String toString() {
        return format(
                """
                    [%s] [%s] : [ %s ]
                """,
                situation, dateTime.format(Formater.DATE_TIME_FORMATTER), nonNull(body) ? body.toString() : message
        );
    }

    public enum Type {
        SUCCESS,
        ERROR
    }

}