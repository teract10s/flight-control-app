package test.task.flightcontrolapp.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public abstract class Database<T> {
    protected Map<Long, T> data = new HashMap<>();

    public abstract boolean save(T element);

    public abstract void saveAll(List<T> elements);

    public Optional<T> findById(Long id){
        return Optional.ofNullable(data.get(id));
    }

    public List<T> getAll() {
        return data.values().stream().toList();
    }

    public boolean deleteById(Long id) {
        if (!data.containsKey(id)) {
            throw new NoSuchElementException("Entity with id " + id + " doesn't exists.");
        }
        data.remove(id);
        return true;
    }

    public Long getNewId() {
        Set<Long> numbers = data.keySet();
        Long smallestMissing = 0L;

        while (numbers.contains(smallestMissing)) {
            smallestMissing++;
        }

        return smallestMissing;
    }
}
