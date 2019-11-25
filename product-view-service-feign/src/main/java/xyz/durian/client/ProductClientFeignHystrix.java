package xyz.durian.client;

import org.springframework.stereotype.Component;
import xyz.durian.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Durian
 * Date: 2019/11/25 17:20
 * Description: 断路器
 */
@Component
public class ProductClientFeignHystrix implements ProductClientFeign
{
    @Override
    public List<Product> productList()
    {
        List<Product> result = new ArrayList<>();
        result.add(new Product(0, "数据服务不可用", 0));
        return result;
    }
}
