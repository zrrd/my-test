package com.example.mytest.LeetCode;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a
 *
 * @author 邵益炯
 * @date 2018/8/6
 */
public class TsetAAA {

  public static void main(String[] args) throws IOException, InterruptedException {
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
    System.out.println(dateFormat.format(date));
  }

}
