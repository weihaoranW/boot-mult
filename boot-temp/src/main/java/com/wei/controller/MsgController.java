package com.wei.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wei.utils.G;
import com.wei.utils.Result;

//@ApiXXXX
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.wei.entity.Msg;
import com.wei.mapper.MsgMapper;
import com.wei.service.MsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;




@RestController
@RequestMapping("/Msg")
@Api("")
public class MsgController {

@Autowired
private MsgService obj;

@GetMapping("/get")
@ApiOperation("get方式按id获取数据")
public Result get(Long id){
   // table.entityPath   msg
   // entity:            Msg
   // table.serviceName: MsgService
   // com.wei.entity.Msg;
   // com.wei.mapper.MsgMapper;
   // com.wei.service.MsgService;
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
public Result insert(@RequestBody Msg data) {
   // entity:            Msg
   // table.serviceName: MsgService
 //G.emt("",data);
 //G.emt("",data.getId());
 return Result.ok(obj.save(data));
}

@PostMapping("/update")
@ApiOperation("post方式修改数据")
public Result update(@RequestBody Msg data) {
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
 QueryWrapper<Msg> wr = new QueryWrapper<>();
 for (String key : p.keySet()) {
  wr.eq(key, p.get(key));
 }

 //
 IPage<Msg> page = new Page<>(pageNo, pageSize);
 return Result.ok(obj.page(page, wr));
}

}
