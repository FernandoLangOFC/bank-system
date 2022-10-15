package bank.system.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

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


    public enum Type {
        SUCCESS,
        ERROR
    }

}