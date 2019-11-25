package xyz.durian;

import brave.sampler.Sampler;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.Cleanup;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Author: Durian
 * Date: 2019/11/22 16:53
 * Description: 5秒不输入，那么就默认使用 8001端口
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication
{
    //Sampler 抽样策略： ALWAYS_SAMPLE 表示持续抽样
    @Bean
    public Sampler defaultSampler()
    {
        return Sampler.ALWAYS_SAMPLE;
    }

    public static void main(String[] args)
    {
        //判断 rabbitMQ 是否启动
        int rabbitMQPort = 5672;
        if(NetUtil.isUsableLocalPort(rabbitMQPort)) {
            System.err.printf("未在端口%d 发现 rabbitMQ服务，请检查rabbitMQ 是否启动", rabbitMQPort );
            System.exit(1);
        }
        int port;
        int defaultPort = 8001;
        Future<Integer> future = ThreadUtil.execAsync(() ->
        {
            int p;
            System.out.println("请于5秒钟内输入端口号, 推荐  8001 、 8002  或者  8003，超过5秒将默认使用 " + defaultPort);
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
        new SpringApplicationBuilder(ProductServiceApplication.class).properties("server.port=" + port).run(args);
    }

}
