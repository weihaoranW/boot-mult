package com.wei.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.common.R;
import com.wei.dto.RegisterDTO;
import com.wei.mq.MQSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息路由规则：四种模式（topic、direct、fanout、header）
 * topic：根据绑定关键字通配符规则匹配、比较灵活
 * direct：默认，根据routingKey完全匹配，好处是先匹配再发送
 * fanout：不需要指定routingkey，相当于群发
 * header：不太常用，可以自定义匹配规则
 *
 * @author
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class MQTestController {

@Autowired
private MQSender sender;

@GetMapping("/sendStr/{str}")
public R sendString(@PathVariable("str") String s) {
 sender.sendString(s);
 return R.ok();
}

@GetMapping(path = "/sendRegister")
public R sendRegister() {
 log.info("-----------enter -sendDirect start--------------------");
 RegisterDTO data = new RegisterDTO();
 data.setMsg("abc");
 data.setMobile("15537902302");
 data.setCode("123");

 sender.sendCustomerRegister(data);
 return R.ok();
}

@GetMapping(path = "/sendFindPWD")
public R sendFindPWD() {
 log.info("-----------enter -sendDirect start--------------------");
 RegisterDTO data = new RegisterDTO();
 data.setMsg("abc");
 data.setMobile("15537902302");
 data.setCode("123");

 sender.sendCustomerFindPWD(data);
 return R.ok();
}

}
