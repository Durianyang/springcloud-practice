package xyz.durian;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Author: Durian
 * Date: 2019/11/25 9:26
 * Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigServerApplication
{
    public static void main(String[] args)
    {
        int port = 8030;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(ConfigServerApplication.class).properties("server.port=" + port).run(args);
    }
}
