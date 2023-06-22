package tr.com.teb.dw.product_app.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import tr.com.teb.dw.product_app.model.entity.Product;
import tr.com.teb.dw.product_app.model.repository.ProductRepository;

import java.util.List;

public abstract class AbstractProductService implements EntityService<Product, Integer>
{
    @Autowired
    protected ProductRepository productRepository;

    // SELECT * FROM PRODUCTS WHERE PRICE >= ?;
    public abstract List<Product> findByPriceGreaterThanEqual(Double price);

    // SELECT * FROM PRODUCTS WHERE PRICE < ?;
    public abstract List<Product> findByPriceLessThan(Double price);

}
