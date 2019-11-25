package xyz.durian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.durian.client.ProductClientFeign;
import xyz.durian.entity.Product;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/11/22 18:04
 * Description:
 */
@Service
public class ProductService
{
    private final ProductClientFeign productClientFeign;
    @Autowired
    public ProductService(ProductClientFeign productClientFeign)
    {
        this.productClientFeign = productClientFeign;
    }

    public List<Product> productList()
    {
        return productClientFeign.productList();
    }
}
