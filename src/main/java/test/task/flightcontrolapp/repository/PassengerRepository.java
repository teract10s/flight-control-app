package test.task.flightcontrolapp.repository;

import java.util.List;
import test.task.flightcontrolapp.database.Database;
import test.task.flightcontrolapp.model.Passenger;

public class PassengerRepository extends Database<Passenger> {

    @Override
    public boolean save(Passenger element) {
        if (element.getId() == null) {
            element.setId(getNewId());
        }
        data.put(element.getId(), element);
        return true;
    }

    @Override
    public void saveAll(List<Passenger> elements) {
        elements.stream()
                .forEach(e -> data.put(e.getId(), e));
    }
}
