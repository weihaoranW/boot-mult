package com.wei.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wei.entity.Employee;
import com.wei.service.IEmployeeService;
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
@ApiOperation("get by id")
public Result get(@RequestParam("id") Long id) {
 return Result.autoObj(obj.getById(id));
}

@PostMapping("/get")
@ApiOperation("get by id")
public Result getPost(Map<String, Object> p) {
 QueryWrapper<Employee> wr = new QueryWrapper<>();
 //
 for (String key : p.keySet()) {
  wr.eq(key, p.get(key));
 }

 //
 return Result.autoObj(obj.getOne(wr));
}

}
