package test.task.flightcontrolapp.repository;

import java.util.List;
import test.task.flightcontrolapp.database.Database;
import test.task.flightcontrolapp.model.Ticket;

public class TicketRepository extends Database<Ticket> {

    @Override
    public boolean save(Ticket element) {
        if (element.getId() == null) {
            element.setId(getNewId());
        }
        data.put(element.getId(), element);
        return true;
    }

    @Override
    public void saveAll(List<Ticket> elements) {
        elements.stream()
                .forEach(e -> data.put(e.getId(), e));
    }
}
