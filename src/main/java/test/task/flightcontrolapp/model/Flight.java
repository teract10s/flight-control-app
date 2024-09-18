package test.task.flightcontrolapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private Long id;

    private String plane;

    private List<Long> ticketsId;
}
