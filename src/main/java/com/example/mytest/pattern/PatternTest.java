package com.example.mytest.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author 邵益炯
 * @date 2018/8/13
 */
public class PatternTest {

  public static void main(String[] args) {
    String pattern = "[\\d|[a-z]|[A-Z]]{6,15}";
    String want = "1456645s";

    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(want);
    if (m.matches()) {
      System.out.println("找到类");
    }
  }

}
