package xyz.durian.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

/**
 * Author: Durian
 * Date: 2019/11/25 17:34
 * Description: 不停访问服务的类,用于测试断路器监控
 */
public class AccessViewService
{
    public static void main(String[] args)
    {
        while (true)
        {
            ThreadUtil.sleep(1000);
            access(8012);
            access(8013);
        }
    }

    private static void access(int port)
    {
        try
        {
            String html= HttpUtil.get(String.format("http://127.0.0.1:%d/productList",port));
            System.out.printf("%d 地址的视图服务访问成功，返回大小是 %d%n" ,port, html.length());
        } catch (Exception e)
        {
            System.err.printf("%d 地址的视图服务无法访问%n",port);
        }
    }
}
