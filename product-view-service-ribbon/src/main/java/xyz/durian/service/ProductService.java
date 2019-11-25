package xyz.durian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.durian.client.ProductClientRibbon;
import xyz.durian.entity.Product;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/11/22 17:19
 * Description:
 */
@Service
public class ProductService
{
    private final ProductClientRibbon ribbon;
    @Autowired
    public ProductService(ProductClientRibbon ribbon)
    {
        this.ribbon = ribbon;
    }

    public List<Product> listProducts()
    {
        return ribbon.productList();
    }
}