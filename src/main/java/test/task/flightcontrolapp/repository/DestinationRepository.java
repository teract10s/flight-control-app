package test.task.flightcontrolapp.repository;

import java.util.List;
import test.task.flightcontrolapp.database.Database;
import test.task.flightcontrolapp.model.Destination;

public class DestinationRepository extends Database<Destination> {

    @Override
    public boolean save(Destination element) {
        if (element.getId() == null) {
            element.setId(getNewId());
        }
        data.put(element.getId(), element);
        return true;
    }

    @Override
    public void saveAll(List<Destination> elements) {
        elements.stream()
                .forEach(e -> data.put(e.getId(), e));
    }
}
