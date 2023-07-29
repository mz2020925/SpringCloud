package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
// @RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatternProperties patternProperties;  // 测试从nacos拉取配置并热更新

    // @Value("${pattern.dateformat}")
    // private String dateformat;

    // @GetMapping("/test1")
    // public void testPattern1(){
    //     System.out.println(dateformat);
    //     // return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
    // }

    @GetMapping("/test2")
    public PatternProperties testPattern2(){
        return patternProperties;
        // System.out.println(patternProperties.getDateformat());
        // return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }



    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id, @RequestHeader(value = "key1", required = false) String key1) {
        System.out.println(key1);
        return userService.queryById(id);
    }
}
