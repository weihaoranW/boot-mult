package ${package.Controller};


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

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};




@RestController
@RequestMapping("/${entity}")
@Api("")
public class ${table.controllerName} {

@Autowired
private ${table.serviceName} obj;

@GetMapping("/get")
@ApiOperation("get方式按id获取数据")
public Result get(Long id){
   // table.entityPath   ${table.entityPath}
   // entity:            ${entity}
   // table.serviceName: ${table.serviceName}
   // ${package.Entity}.${entity};
   // ${package.Mapper}.${table.mapperName};
   // ${package.Service}.${table.serviceName};
   // ${superServiceImplClassPackage};

   return Result.autoObj(obj.getById(id));
}

@GetMapping("/del")
@ApiOperation("按id删除单个对象")
public Result del(@RequestParam("id") Long id) {

 return Result.autoBool(obj.removeById(id));
}


@PostMapping("/insert")
@ApiOperation("post方式新增数据")
public Result insert(@RequestBody ${entity} data) {
   // entity:            ${entity}
   // table.serviceName: ${table.serviceName}
 //G.emt("",data);
 //G.emt("",data.getId());
 return Result.ok(obj.save(data));
}

@PostMapping("/update")
@ApiOperation("post方式修改数据")
public Result update(@RequestBody ${entity} data) {
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
 QueryWrapper<${entity}> wr = new QueryWrapper<>();
 for (String key : p.keySet()) {
  wr.eq(key, p.get(key));
 }

 //
 IPage<${entity}> page = new Page<>(pageNo, pageSize);
 return Result.ok(obj.page(page, wr));
}

}
