package com.wei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.wei.mapper")
@ServletComponentScan
public class BootPGApp {

public static void main(String[] args) {
 SpringApplication.run(BootPGApp.class, args);
}

}
