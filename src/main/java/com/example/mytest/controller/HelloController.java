package com.example.mytest.controller;

import com.example.mytest.domain.GameDetail;
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
  public String hello() {
    return new GameDetail(1).selectById().toString();
  }

}
