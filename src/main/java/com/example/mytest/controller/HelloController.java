package com.example.mytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * docker.
 *
 * @author shaoyijiong
 * @date 2018/7/26
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello world";
  }
}
