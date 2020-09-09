package com.example.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2020/9/9
 */
@RestController
public class TestController {

  private final Go go;

  public TestController(Go go) {
    this.go = go;
  }

  @RequestMapping("a")
  public void a() {
    go.a();
    go.b();
  }
}
