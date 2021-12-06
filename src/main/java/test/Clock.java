package test;

import lombok.Data;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "clock", eager = true)
@ApplicationScoped
@Data
public class Clock {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy");
    DateTimeFormatter hf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime localDate;

    public LocalDateTime getLocalDate() {
        return LocalDateTime.now();
    }
}
