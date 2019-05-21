package com.wei.controller;


import com.wei.entity.Msg;
import com.wei.service.MsgService;
import com.wei.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/msg")
public class MsgController {

@Autowired
private MsgService obj;

@GetMapping("/get")
public Result get(Long id){
   // entity:            Msg
   // table.serviceName: MsgService
   //
   return Result.ok(obj.getById(id));
}


}
