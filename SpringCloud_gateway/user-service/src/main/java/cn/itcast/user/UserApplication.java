package cn.itcast.user;

import cn.itcast.user.config.PatternProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@MapperScan("cn.itcast.user.mapper")
@SpringBootApplication
@EnableConfigurationProperties(PatternProperties.class)
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
