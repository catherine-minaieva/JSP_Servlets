package ua.goit.repositoty;

import ua.goit.model.BaseEntity;

import java.util.Collection;

public interface BaseRepository <E extends BaseEntity<ID>, ID> {

   Collection<E> findAll();

    void deleteById(ID id);

    E findById(ID id);

    E create(E e);

    E update(ID id, E e);

}
