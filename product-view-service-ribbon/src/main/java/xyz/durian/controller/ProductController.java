package xyz.durian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.durian.entity.Product;
import xyz.durian.service.ProductService;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/11/22 17:23
 * Description:
 */
@Controller
public class ProductController
{
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @RequestMapping("/productList")
    public String productList(Model model)
    {
        List<Product> productList = productService.listProducts();
        model.addAttribute("productList", productList);
        return "productList";
    }
}
