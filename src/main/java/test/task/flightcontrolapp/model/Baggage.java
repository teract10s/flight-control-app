package test.task.flightcontrolapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Baggage {
    private Long id;

    private float weight;

    private Long passengerId;
}
