package xyz.durian.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.durian.entity.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Author: Durian
 * Date: 2019/11/22 17:15
 * Description: Ribbon 客户端， 通过 restTemplate 访问 http://PRODUCT-DATA-SERVICE/products
 */
@Component
public class ProductClientRibbon
{
    private final RestTemplate restTemplate;
    @Autowired
    public ProductClientRibbon(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public List<Product> productList()
    {
        //消除警告
        Product[] products = restTemplate.getForObject("http://PRODUCT-DATA-SERVICE/productList", Product[].class);
        return Arrays.asList(Objects.requireNonNull(products));
    }
}
