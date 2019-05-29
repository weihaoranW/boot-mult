package com.wei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.wei.mapper")
@ServletComponentScan
public class TempApplication {

public static void main(String[] args) {
 SpringApplication.run(TempApplication.class, args);
}

}
