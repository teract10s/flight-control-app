package test.task.flightcontrolapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Flight {
    private Long id;

    private String plane;

    private List<Long> ticketsId;
}
