package tr.com.teb.dw.product_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.teb.dw.product_app.model.entity.Product;
import tr.com.teb.dw.product_app.model.service.AbstractProductService;

import java.util.Date;
import java.util.List;

@RequestMapping("api/product")
@RestController
public class ProductController
{
    /*
        DI (dependency injection'ın) sağlıklı bir şekilde kurulması,
        DIP (dependency inversion principle) ile birliktedir.

        Buradaki @Autowired unsur, ProductService (somut yapı) ile değil,
        onun soyut atası AbstractProductService tipinde tanımlandı.

        Buradaki @Autowired unsur, EntityService tipinde de tanımlanabilir.
        Bu da DIP'e uygundur. Ancak bu durumda, AbstractProductService içindeki metotların
        çağrımı için down-casting ile uğraşılması gerekecektir.
     */
    @Autowired
    private AbstractProductService productService;

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteByID(@PathVariable(name = "id") Integer productID)
    {
        Product product = productService.findByID(productID);

        if(product != null)
        {
            productService.deleteByID(productID);
            return ResponseEntity.ok("Product ID " + productID + " has been deleted.");
        }
        else
        {
            return new ResponseEntity<>("Product is not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findByID(@PathVariable(name = "id") Integer productID)
    {
        Product product = productService.findByID(productID);

        return product != null
                ? ResponseEntity.ok(product)
                : new ResponseEntity<>("Product is not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> getAll()
    {
        List<Product> productList = productService.getAll();

        return  !productList.isEmpty()
                ? ResponseEntity.ok(productList)
                : new ResponseEntity<>("Product list is empty.", HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Product product)
    {
        product.setCreated(new Date());

        Product savedProduct = productService.save(product);

        return savedProduct != null
                ? new ResponseEntity<>(savedProduct, HttpStatus.CREATED)
                : new ResponseEntity<>("Product is null", HttpStatus.NOT_ACCEPTABLE);
    }
}
