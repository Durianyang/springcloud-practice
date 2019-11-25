package xyz.durian.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

/**
 * Author: Durian
 * Date: 2019/11/25 17:34
 * Description: 不停访问服务的类
 */
public class AccessViewService
{
    public static void main(String[] args)
    {
        while (true)
        {
            ThreadUtil.sleep(1000);
            try {
                String html= HttpUtil.get("http://127.0.0.1:8012/productList");
                System.out.println("html length:" + html.length());
            }
            catch(Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
