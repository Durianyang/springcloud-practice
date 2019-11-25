package xyz.durian;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.Cleanup;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Author: Durian
 * Date: 2019/11/22 17:26
 * Description: 启动类，用 restTemplate 这个工具来做负载均衡
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class ProductViewServiceRibbonApplication
{
    public static void main(String[] args)
    {
        int port;
        int defaultPort = 8010;
        Future<Integer> future = ThreadUtil.execAsync(() ->
        {
            int p;
            System.out.println("请于5秒钟内输入端口号, 推荐  8010  超过5秒将默认使用 " + defaultPort);
            @Cleanup Scanner scanner = new Scanner(System.in);
            while (true)
            {
                String strPort = scanner.nextLine();
                if (!NumberUtil.isInteger(strPort))
                {
                    System.err.println("只能是数字");
                } else
                {
                    p = Convert.toInt(strPort);
                    break;
                }
            }
            return p;
        });
        try
        {
            port = future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e)
        {
            port = defaultPort;
        }
        if (!NetUtil.isUsableLocalPort(port))
        {
            System.err.printf("端口%d被占用了，无法启动%n", port);
            System.exit(1);
        }
        new SpringApplicationBuilder(ProductViewServiceRibbonApplication.class).properties("server.port=" + port).run(args);
    }

    @Bean
    //做负载均衡
    @LoadBalanced
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
