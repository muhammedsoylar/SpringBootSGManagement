package tr.com.teb.dw.product_app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.teb.dw.product_app.model.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
    // SELECT * FROM PRODUCTS WHERE PRICE >= ?;
    List<Product> findByPriceGreaterThanEqual(Double price);

    // SELECT * FROM PRODUCTS WHERE PRICE < ?;
    List<Product> findByPriceLessThan(Double price);
}
