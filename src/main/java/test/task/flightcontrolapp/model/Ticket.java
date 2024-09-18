package test.task.flightcontrolapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket  {
    private Long id;

    private double price;

    private Long planeId;

    private String seat;

    private Long passengerId;
}
