package com.example.mytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * docker.
 *
 * @author shaoyijiong
 * @date 2018/7/26
 */
@RestController
public class HelloController {

  @RequestMapping("/hello")
  public String hello(@RequestBody String jsonData) {
    return "hello world";
  }

  @GetMapping("/aa")
  public String aa() {
    String a = "{\"name':'hhh','age':18,'hobby':['game','read','food'],"
        + "'pet':{'type':'cat','name':'JACK'}}";
    String json = "{\"code\":\"0\",\"ConfigureAdd\":\"http://192.168.10.3:8080/download\",\"ConfigureMD5\":\"1a9204585e0a90e1ec4bd7b119bf5c18\"}";
    return json;
  }
}
