package ra.service;

import java.util.List;

public interface IGennericService<T,E> {
        List<T> findAll();
        T findById(E id);
        void save(T t);
        void delete(E id);
}
