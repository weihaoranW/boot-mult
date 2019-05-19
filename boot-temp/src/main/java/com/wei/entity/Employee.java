package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Data
public class Employee implements Serializable {

private static final long serialVersionUID = 1L;

@TableId(value = "id", type = IdType.AUTO)
private Long id;

private String cname;

private String addr;

@TableField("createTime")
private Date createTime;

private Integer age;

@Override
public String toString() {
 return "Employee{" +
         "id=" + id +
         ", cname=" + cname +
         ", addr=" + addr +
         ", createTime=" + createTime +
         ", age=" + age +
         "}";
}
}
