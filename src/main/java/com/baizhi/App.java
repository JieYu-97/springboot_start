package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@MapperScan(basePackages = "com.baizhi.dao")
@SpringBootApplication
@EnableRedisHttpSession
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
