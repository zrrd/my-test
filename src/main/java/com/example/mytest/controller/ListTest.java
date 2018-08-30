package com.example.mytest.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邵益炯
 * @date 2018/8/28
 */
public class ListTest {

  public static void main(String[] args) {
    List<String> a = new ArrayList<>();
    List<Object> b = new ArrayList<>();

    System.out.println(a instanceof List);
  }
}
