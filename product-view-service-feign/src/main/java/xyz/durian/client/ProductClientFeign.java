package xyz.durian.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.durian.entity.Product;

import java.util.List;

/**
 * Author: Durian
 * Date: 2019/11/22 18:03
 * Description:
 */
@FeignClient(value = "PRODUCT-DATA-SERVICE")
public interface ProductClientFeign
{
    @GetMapping("/productList")
    List<Product> productList();
}
