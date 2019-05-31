package com.wei.controller;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wei.utils.G;
import com.wei.utils.Result;

//@ApiXXXX
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.wei.entity.Test;
import com.wei.mapper.TestMapper;
import com.wei.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/Test")
@Api("")
public class TestController {

@Autowired
private TestService obj;

@GetMapping("/get/{id}")
@ApiOperation("get方式按id获取数据")
public Result get(@PathVariable("id") Long id) {
 // table.entityPath   test
 // entity:            Test
 // table.serviceName: TestService
 // com.wei.entity.Test;
 // com.wei.mapper.TestMapper;
 // com.wei.service.TestService;
 // com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

 return Result.autoObj(obj.getById(id));
}

@GetMapping("/del")
@ApiOperation("按id删除单个对象")
public Result del(@RequestParam("id") Long id) {

 return Result.autoBool(obj.removeById(id));
}

@PostMapping("/insert")
@ApiOperation("post方式新增数据")
public Result insert(@RequestBody Test data) {
 // entity:            Test
 // table.serviceName: TestService
 //G.emt("",data);
 //G.emt("",data.getId());
 return Result.ok(obj.save(data));
}

@PostMapping("/update")
@ApiOperation("post方式修改数据")
public Result update(@RequestBody Test data) {
 //G.emt("",data);
 //G.emt("",data.getId());
 return Result.ok(obj.updateById(data));
}

@PostMapping("/list")
@ApiOperation("post方式获取多个数据，以list方式返回")
public Result list(@RequestBody Map<String, Object> p) {
 //G.emt("",p);
 //G.emt("",);
 //
 return Result.ok(obj.listByMap(p));
}

@PostMapping("/listPage")
@ApiOperation("post方式获取多个分而数据，以list方式返回")
public Result listPage(@RequestBody Map<String, Object> p) {
 //G.emt("",p);
 //G.emt("",);
 int pageNo = G.get_Integer_of_map(p, "pageNo", 1);
 int pageSize = G.get_Integer_of_map(p, "pageSize",
  10);

 //map 2 wrapper
 QueryWrapper<Test> wr = new QueryWrapper<>();
 for (String key : p.keySet()) {
  wr.eq(key, p.get(key));
 }

 //
 IPage<Test> page = new Page<>(pageNo, pageSize);
 return Result.ok(obj.page(page, wr));
}

}
