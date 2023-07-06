package repository;

import model.Entity;
import model.Propunere;

public interface Repository<E extends Entity> {
    E findOne(int id);

    Iterable<E> findAll();

    E save(E entity);

    void delete(int id);

    void update(E entity);

}