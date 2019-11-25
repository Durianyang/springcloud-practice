package xyz.durian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.durian.entity.Product;
import xyz.durian.service.ProductService;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/11/22 16:52
 * Description:
 */
@RestController
public class ProductController
{
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }
    @RequestMapping("/productList")
    public List<Product> productList()
    {
        return productService.listProducts();
    }

}
