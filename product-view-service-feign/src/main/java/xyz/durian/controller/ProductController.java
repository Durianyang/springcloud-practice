package xyz.durian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class ProductController
{
    @Value("${version}")
    String version;
    @Value("${content}")
    String content;

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @RequestMapping("/productList")
    public String productList(Model model)
    {
        List<Product> productList = productService.productList();
        model.addAttribute("productList", productList);
        model.addAttribute("version", version);
        model.addAttribute("content", content);
        return "productList";

    }
}
