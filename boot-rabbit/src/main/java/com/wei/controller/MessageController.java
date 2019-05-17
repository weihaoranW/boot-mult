package com.wei.controller;

import com.alibaba.fastjson.JSONObject;
import com.wei.commons.R;
import com.wei.dto.RegisterDTO;
import com.wei.send.DirectSender;
import com.wei.send.DirectSenderTest1;
import com.wei.send.DirectSenderTestRegister;
import com.wei.send.FanoutSender;
import com.wei.send.TopicSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
 * @author cm_wang
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class MessageController {

@Autowired
private TopicSender topicSender;

@Autowired
private DirectSender directSender;

@Autowired
private FanoutSender fanoutSender;

//------------------------------------------
@Autowired
private DirectSenderTest1 directSenderTest1;
@Autowired
private DirectSenderTestRegister senderRegister;

/**
 * topic 模式
 *
 * @return
 */
@GetMapping("/sendTopic")
public R sendTopic(@RequestParam("obj") JSONObject obj) {
 topicSender.sendTopic(obj);
 return R.ok();
}

@GetMapping("/send/{data}")
public R send(@PathVariable("data") String s) {
 directSenderTest1.sendDirect(s);
 return R.ok();
}

@GetMapping(path = "/sendRegister")
public R sendRegister() {
 log.info("-----------enter -sendDirect start--------------------");
 RegisterDTO data = new RegisterDTO();
 data.setMsg("abc");
 data.setMobile("15537902302");
 data.setCode("123");

 senderRegister.sendDirect(data);
 return R.ok();
}

/**
 * direct 模式 <strong>发送者与接收者的Queue名字一定要相同，否则接收收不到消息</strong>
 *
 * @return
 */
@GetMapping("/sendDirect")
public R sendDirect(@RequestParam("obj") JSONObject obj) {

 for (int i = 0; i < 10; i++) {
  directSender.sendDirect(obj);
 }
 return R.ok();
}

/**
 * fanout模式
 *
 * @return
 */
@GetMapping("/sendFanout")
public R sendFanout(@RequestParam("obj") JSONObject obj) {
 for (int i = 0; i < 10; i++) {
  fanoutSender.sendFanout(obj);
 }
 return R.ok();
}
}
