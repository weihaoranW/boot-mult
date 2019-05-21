package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/${table.entityPath}")
public class ${table.controllerName} {

@Autowired
private ${table.serviceName} obj;

@GetMapping("/get")
public Result get(Long id){
   // entity:            ${entity}
   // table.serviceName: ${table.serviceName}
   //
   return Result.autoObj(obj.getById(id));
}



}
