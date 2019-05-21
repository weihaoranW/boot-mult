package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@JsonSerialize(using = LocalDateTimeSerializer.class)
private LocalDateTime createTime;

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
