package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * .
 * @author shaoyijiong
 */
@SpringBootApplication
@MapperScan(value = "com.example.mapper")
public class DataProduceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DataProduceApplication.class, args);
  }
}
