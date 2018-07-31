package com.example.mytest.jsoup;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 测试jsoup
 *
 * @author 邵益炯
 * @date 2018/7/31
 */
public class JsoupTest {

  public static void main(String[] args) throws Exception {
    String html = "http://news.163.com/photoview/00AO0001/2295329.html#p=DO1KF9T400AO0001NOS";
    Document doc = Jsoup.connect(html).get();
    System.out.println(doc.title());
    //System.out.println(doc);
  }

}
