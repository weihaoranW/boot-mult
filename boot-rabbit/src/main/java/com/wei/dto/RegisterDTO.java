package com.wei.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RegisterDTO implements Serializable {

public RegisterDTO() {

}

public RegisterDTO(@JsonProperty("text") String mobile,
                   @JsonProperty("priority") String msg,
                   @JsonProperty("secret") String code) {
 this.mobile = mobile;
 this.msg = msg;
 this.code = code;
}

private String mobile;
private String code;
private String msg;

public String getMobile() {
 return mobile;
}

public void setMobile(String mobile) {
 this.mobile = mobile;
}

public String getCode() {
 return code;
}

public void setCode(String code) {
 this.code = code;
}

public String getMsg() {
 return msg;
}

public void setMsg(String msg) {
 this.msg = msg;
}

@Override
public String toString() {
// return "code:" + code +
//         "mobile:" + mobile +
//         "msg:" + msg;
 //return JSONObject.toJSON(this);
 return JSONObject.toJSONString(this);
}

}
