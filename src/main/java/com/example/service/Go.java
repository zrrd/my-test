package com.example.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.mapper.T1Mapper;
import com.example.mapper.T2Mapper;
import org.springframework.stereotype.Service;

/**
 * @author shaoyijiong
 * @date 2020/9/9
 */
@Service
public class Go {

  private final T1Mapper t1Mapper;
  private final T2Mapper t2Mapper;

  public Go(T1Mapper t1Mapper, T2Mapper t2Mapper) {
    this.t1Mapper = t1Mapper;
    this.t2Mapper = t2Mapper;
  }

  @DS("slave_1")
  public void a() {
    System.out.println(t2Mapper.count());
  }

  public void b() {
    System.out.println(t1Mapper.count());
  }
}
