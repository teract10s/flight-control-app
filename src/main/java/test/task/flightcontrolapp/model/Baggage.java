package test.task.flightcontrolapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Baggage {
    private Long id;

    private float weight;

    private Long passengerId;
}
