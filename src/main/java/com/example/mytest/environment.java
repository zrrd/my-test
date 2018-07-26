package com.example.mytest;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * environment测试.
 *
 * @author shaoyijiong
 * @date 2018/7/26
 */
@Component
public class environment implements EnvironmentAware {

  private Environment environment;



  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
