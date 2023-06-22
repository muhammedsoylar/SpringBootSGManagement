package tr.com.teb.dw.product_app.model.service;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import tr.com.teb.dw.product_app.model.entity.Product;
import tr.com.teb.dw.product_app.utility.Util;

import java.util.List;

@Service
public class ProductService extends AbstractProductService
{
    @Override
    public List<Product> findByPriceGreaterThanEqual(Double price)
    {
        try
        {
            return productRepository.findByPriceGreaterThanEqual(price);
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public List<Product> findByPriceLessThan(Double price)
    {
        try
        {
            return productRepository.findByPriceLessThan(price);
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public Product findByID(Integer id)
    {
        try
        {
            return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product is not found"));
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }

        catch (NullPointerException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }

        catch (RuntimeException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }

    @Override
    public void deleteByID(Integer id)
    {
        try
        {
            productRepository.deleteById(id);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
        }
    }

    @Override
    public List<Product> getAll()
    {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product)
    {
        try
        {
            return productRepository.save(product);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
        catch (OptimisticLockingFailureException e)
        {
            Util.showGeneralExceptionInfo(e);
            return null;
        }
    }
}
