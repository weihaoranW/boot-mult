package com.wei.controller;

import com.wei.common.R;
import com.wei.entity.UserTest;
import com.wei.redis.utils.RedisConstants;
import com.wei.redis.utils.RedisUtil;
import com.wei.redis.utils.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
@Slf4j
public class RedisController {
@Autowired
RedisUtil redis;

@RequestMapping(value = "getRedis", method = RequestMethod.POST)
public R getRedis() {

 redis.set("20182018", "这是一条测试数据", RedisConstants.datebase1);
 Long resExpire = redis.expire("20182018", 60, RedisConstants.datebase1);
 //设置key过期时间
 log.info("resExpire=" + resExpire);
 String res = redis.get("20182018", RedisConstants.datebase1);

 //测试Redis保存对象
 UserTest u = new UserTest();
 u.setAge(24);
 u.setName("冯绍峰");
 redis.set("20181017".getBytes(), SerializeUtil.serialize(u), RedisConstants.datebase1);
 byte[] user = redis.get("20181017".getBytes(), RedisConstants.datebase1);
 UserTest data = (UserTest) SerializeUtil.unserialize(user);
 System.out.println("user=" + data.toString());

 return R.ok(data);
}

@GetMapping("/testGet")
public R testGet() {

 UserTest data = redis.xGet("whr-testSet-key", UserTest.class);
 return R.ok(data);
}

@PostMapping("testSet")
public R testGet(@RequestBody UserTest data) {

 return R.ok(redis.xSet("whr-testSet-key", data));
}

}
