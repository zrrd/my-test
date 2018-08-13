package com.example.mytest.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2018/1/2
 */
@RestController
public class HtmlController {

  private Logger logger = LoggerFactory.getLogger(HtmlController.class);

  @RequestMapping("/hi")
  public String hello() {
    return "hi,欢迎来到解析html微服务！";
  }

  //@Cacheable(value = "urls",key = "'url_'.concat(#root.args[0])")
  @RequestMapping("/parserHtml")
  public Map<String, Object> parserHtml(String url) {
    Map<String, Object> map = new HashMap<>(3);
    if (StringUtils.isEmpty(url)) {
      map.put("msg", "url不能为空！");
      logger.error("------传入空url------");
      map.put("code", 0);
      return map;
    }
    try {
      url = URLDecoder.decode(url, "utf-8");
      logger.info("----------解析url【{}】开始---------", url);
      Map<String, String> data = new HashMap<>(3);
      Document doc = Jsoup.parse(new java.net.URL(url), 3000);
      String title = doc.title();
      String description;
      try {
        description = doc.select("meta[name=description]").get(0).attr("content");
      } catch (Exception e) {
        e.printStackTrace();
        description = title;
      }
      logger.info("=====解析成功，标题【{}】，摘要【{}】", title, description);
      map.put("msg", "解析成功！");
      map.put("code", 1);
      if (StringUtils.isEmpty(description)) {
        data.put("description", title);
      } else {
        data.put("description", description);
      }
      data.put("title", title);
      map.put("data", data);

      //都没有符合的,返回小图标
      String iconUrl;
      String urlPrefix = "http://" + StringUtils.substringBetween(url, "//", "/");
      Elements icons = doc.select("link[rel=shortcut icon]");
      if (icons.size() > 0) {
        String relativePath = icons.get(0).attr("href");
        if (relativePath.startsWith("//")) {
          iconUrl = "https:" + relativePath;
        } else {
          if (!relativePath.startsWith("/")) {
            iconUrl = urlPrefix + "/" + relativePath;
          } else {
            iconUrl = urlPrefix + relativePath;
          }
        }
      } else {
        iconUrl = urlPrefix + "/favicon.ico";
      }
      data.put("faviconIco", iconUrl);
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("解析失败！！！", e);
      map.put("code", 500);
      map.put("msg", e);
    }
    return map;
  }
}
