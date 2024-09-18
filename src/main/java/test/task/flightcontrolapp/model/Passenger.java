package test.task.flightcontrolapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passenger {
    private Long id;

    private String name;

    private String surname;

    private List<Long> ticketsId;
}
