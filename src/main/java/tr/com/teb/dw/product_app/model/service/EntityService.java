package tr.com.teb.dw.product_app.model.service;

import java.util.List;

public interface EntityService<E, I>
{
    E findByID(I id);

    void deleteByID(I id);

    List<E> getAll();

    E save(E entity);
}
