package com.wei.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wei.entity.TUser;
import com.wei.entity.User;
import com.wei.service.ITUserService;
import com.wei.util.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "user表有关信息接口")
@RestController
@Logger
@RequestMapping("/User")
public class TUserController {
@Autowired
ITUserService obj;

@ApiOperation(value = "显示全部用户")
@GetMapping("/list")
public List<?> selectAll() {
 //String codeMessage = "显示所有用户";
 List<TUser> l = obj.list();
 //return new RestResponse<>(0, codeMessage, users);
 return l;
}

@ApiOperation(value = "添加用户")
@PostMapping(value = "/add")
public RestResponse<?> addUser(@RequestBody @Valid TUser user) {
 obj.save(user);
 return new RestResponse<>(0, "SUCCESS", null);
}

@ApiOperation(value = "删除用户")
@DeleteMapping(value = "/del")
public RestResponse<?> deleteUser(@RequestParam int id) {
 obj.removeById(id);
 return new RestResponse<>(0, "SUCCESS", null);
}

@ApiOperation(value = "按名字查询用户")
@GetMapping(value = "/listByName")
public RestResponse<?> selectUserByName(@RequestParam String name) {
 String codeMessage = "按名字查询用户";
 QueryWrapper wrapper = new QueryWrapper();
 wrapper.eq("name", name);
 List<TUser> users = obj.list(wrapper);
 return new RestResponse<>(0, codeMessage, users);
}

@ApiOperation(value = "修改用户信息")
@PutMapping(value = "/update")
public RestResponse<?> updateUser(@RequestBody @Valid TUser user) {
 boolean flag = obj.updateById(user);
 if (flag) {
  return new RestResponse<>(0, "更新成功", null);
 } else return new RestResponse<>(0, "更新失败", null);
}

@PostMapping("/dologin")
public String dologin(
 @RequestBody User user
) {
 System.out.println("---####--enter dologin-------");
 return user.getUsername() + ":" + user.getPassword();
}
}
