package test.task.flightcontrolapp.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {
    private Long id;

    private String name;

    private List<Long> flightsId;
}
