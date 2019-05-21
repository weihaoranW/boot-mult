package com.wei.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wei.entity.Employee;
import com.wei.service.IEmployeeService;
import com.wei.util.G;
import com.wei.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author weihaoran
 * @since 2019-05-19
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

@Autowired
private IEmployeeService obj;

@GetMapping("/get")
@ApiOperation("按id获取音箱数据")
public Result get(@RequestParam("id") Long id) {

 return Result.autoObj(obj.getById(id));
}

@GetMapping("/del")
@ApiOperation("按id删除单个对象")
public Result del(@RequestParam("id") Long id) {

 return Result.autoBool(obj.removeById(id));
}

@PostMapping("/get")
@ApiOperation("按id获取单个对象内容，多个查询条件")
public Result getPost(Map<String, Object> p) {
 //
 QueryWrapper<Employee> wr = new QueryWrapper<>();
 for (String key : p.keySet()) {
  wr.eq(key, p.get(key));
 }

 return Result.ok(obj.getOne(wr));
}

@PostMapping("/insert")
@ApiOperation("post方式新增数据")
public Result insert(Employee data) {
 //G.emt("",data);
 //G.emt("",data.getId());
 return Result.ok(obj.save(data));
}

@PostMapping("/update")
@ApiOperation("post方式修改数据")
public Result update(Employee data) {
 //G.emt("",data);
 //G.emt("",data.getId());
 return Result.ok(obj.updateById(data));
}

@PostMapping("/list")
@ApiOperation("post方式获取多个数据，以list方式返回")
public Result list(Map<String, Object> p) {
 //G.emt("",p);
 //G.emt("",);
 //
 return Result.ok(obj.listByMap(p));
}

@PostMapping("/listPage")
@ApiOperation("post方式获取多个分而数据，以list方式返回")
public Result listPage(Map<String, Object> p) {
 //G.emt("",p);
 //G.emt("",);
 int pageNo = G.get_Integer_of_map(p, "pageNo", 1);
 int pageSize = G.get_Integer_of_map(p, "pageSize",
  20);

 //map 2 wrapper
 QueryWrapper<Employee> wr = new QueryWrapper<>();
 for (String key : p.keySet()) {
  wr.eq(key, p.get(key));
 }

 //
 IPage<Employee> page = new Page<>(pageNo, pageSize);
 return Result.ok(obj.page(page, wr));
}

}
