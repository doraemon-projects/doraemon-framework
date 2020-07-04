package org.doraemon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 应用启动类
 * @author: fengwenping
 * @date: 2020-05-16 12:47
 */
@SpringBootApplication
@MapperScan("org.doraemon.**.dao")
public class DoraemonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoraemonApplication.class, args);
    }
}
