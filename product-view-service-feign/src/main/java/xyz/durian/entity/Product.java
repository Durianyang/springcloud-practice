package xyz.durian.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: Durian
 * Date: 2019/11/22 16:44
 * Description:
 */
@Data
public class Product implements Serializable
{
    private static final long serialVersionUID = 1443091658399693472L;

    public Product() { }

    public Product(int id, String name, int price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private int id;
    private String name;
    private int price;
}
