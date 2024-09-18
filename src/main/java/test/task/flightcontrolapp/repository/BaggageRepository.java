package test.task.flightcontrolapp.repository;

import java.util.List;
import test.task.flightcontrolapp.database.Database;
import test.task.flightcontrolapp.model.Baggage;

public class BaggageRepository extends Database<Baggage> {

    @Override
    public boolean save(Baggage element) {
        if (element.getId() == null) {
            element.setId(getNewId());
        }
        data.put(element.getId(), element);
        return true;
    }

    @Override
    public void saveAll(List<Baggage> elements) {
        elements.stream()
                .forEach(e -> data.put(e.getId(), e));
    }
}
