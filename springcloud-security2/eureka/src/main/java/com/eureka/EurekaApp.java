package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author recheard
 * @description:
 * @date 2021/4/2614:56
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {

    //@EnableEurekaServer 表示开启Eureka服务，开启注册中心
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class, args);
    }
}
