package test.task.flightcontrolapp.repository;

import java.util.List;
import test.task.flightcontrolapp.database.Database;
import test.task.flightcontrolapp.model.Flight;

public class FlightRepository extends Database<Flight> {

    @Override
    public boolean save(Flight element) {
        if (element.getId() == null) {
            element.setId(getNewId());
        }
        data.put(element.getId(), element);
        return true;
    }

    @Override
    public void saveAll(List<Flight> elements) {
        elements.stream()
                .forEach(e -> data.put(e.getId(), e));
    }
}
