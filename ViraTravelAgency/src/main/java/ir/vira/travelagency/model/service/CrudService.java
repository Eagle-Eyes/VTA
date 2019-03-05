package ir.vira.travelagency.model.service;

import java.util.List;

public interface CrudService<T> {

    public T add(T t);

    public T remove(Long id);

    public T edit(T t);

    public T get(Long id);

    public List<T> list();
}
