package com.moefor.demo.springboot_jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
@RequestMapping("/")
public String index() {
 return "Hello Spring Boot!";
}

@RequestMapping("/test")
public String test() {
 return "test is called";
}

}
