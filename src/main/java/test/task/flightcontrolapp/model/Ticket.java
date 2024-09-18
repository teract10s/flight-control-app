package test.task.flightcontrolapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket  {
    private Long id;

    private double price;

    private Long planeId;

    private String seat;

    private Long passengerId;
}
