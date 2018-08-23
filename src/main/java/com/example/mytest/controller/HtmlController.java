package com.example.mytest.controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2018/1/2
 */
@Slf4j
@RestController
public class HtmlController {

  /**
   * 模拟手机访问
   */
  private static final String USER_AGENT = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
  /**
   * 解析公众号相关
   */
  private static final Pattern MSG_DESC = Pattern.compile("var msg_desc.*;");
  private static final Pattern MSG_CDN_URL = Pattern.compile("var msg_cdn_url.*;");
  private static final String WX_URL = "mp.weixin.qq.com";
  /**
   * 图片大小匹配
   */
  private static final Integer WIDTH = 300;
  private static final Integer HEIGHT = 300;
  private static final String HTTP = "http";


  @GetMapping("/parserHtml")
  public Map<String, String> parserHtml(@NotBlank(message = "url不能为空") String url) {
    Map<String, String> data = new HashMap<>(3);

    log.info("----------解析url【{}】开始---------", url);
    try {
      url = URLDecoder.decode(url, "utf-8");
      Document doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(3000).get();
      String title = doc.title();
      data.put("title", title);

      if (url.contains(WX_URL)) {
        parserWXHtml(doc, data);
      } else {

        //获取描述
        String description = title;
        Elements elements = doc.select("meta[name=description]");
        if (!elements.isEmpty()) {
          description = Optional.ofNullable(elements.get(0).attr("content")).orElse(title);
        }
        data.put("description", description);

        //获取图片
        data.put("img", getImg(doc, url));
      }
      log.info("=====解析成功,标题[{}],摘要[{}],图片[{}]====",
          data.get("title"), data.get("description"), data.get("img"));
    } catch (Exception e) {
      throw new RuntimeException("链接解析失败");
    }
    return data;
  }

  /**
   * 解析微信公众号链接
   */
  private void parserWXHtml(Document doc, Map<String, String> data) {
    try {
      Elements elements = doc.select("script[type=text/javascript]");
      elements.forEach(e -> {
        if (e.toString().contains("msg_desc")) {
          String text = e.toString();

          //查找描述
          Matcher msgDesc = MSG_DESC.matcher(text);
          if (msgDesc.find()) {
            String description = msgDesc.group();
            description = StringUtils.substringBetween(description, "\"");
            if (StringUtils.isBlank(description)) {
              description = data.get("title");
            }
            data.put("description", description);
          }

          //查找封面图片
          Matcher msgCdnUrl = MSG_CDN_URL.matcher(text);
          if (msgCdnUrl.find()) {
            String img = msgCdnUrl.group();
            img = StringUtils.substringBetween(img, "\"");
            data.put("img", img);
          }
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("链接解析失败");
    }
  }

  /**
   * 获得图片
   */
  private String getImg(Document doc, String url) {
    //获取图片
    String img;

    //有封面图片返回封面图片
    Elements coverImage = doc.select("meta[name=cover-image]");
    if (!coverImage.isEmpty()) {
      img = coverImage.get(0).attr("content");
      return img;
    }

    //没有封面图片返回第一张大小大于300*300的图片
    Elements imgElements = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
    for (Element element : imgElements) {
      img = element.attr("src");
      if (StringUtils.isNoneBlank(img)) {
        //有的url缺少http前缀
        if (!img.contains(HTTP)) {
          img = HTTP + ":" + img;
        }
        if (meetTheSize(img)) {
          return img;
        }
      }
    }

    //都没有返回小图标
    Elements icons = doc.select("link[rel=shortcut icon]");
    String urlPrefix = "http://" + StringUtils.substringBetween(url, "//", "/");
    if (!icons.isEmpty()) {
      String relativePath = icons.get(0).attr("href");
      //以'//'开头的返回绝对路径
      if (relativePath.startsWith("//")) {
        img = "https:" + relativePath;
      } else if (relativePath.startsWith(HTTP)) {
        img = relativePath;
      } else {
        //以'/'开头的或者不以'/'开头的返回相对路径
        if (!relativePath.startsWith("/")) {
          img = urlPrefix + "/" + relativePath;
        } else {
          img = urlPrefix + relativePath;
        }
      }
    } else {
      //不通过页面设置
      img = urlPrefix + "/favicon.ico";
    }

    return img;
  }

  /**
   * 判断是否符合尺寸要求
   */
  private boolean meetTheSize(String imgSrc) {
    BufferedImage sourceImg;
    try {
      URL url = new URL(imgSrc);
      // 根据url获取BufferImage对象
      sourceImg = ImageIO.read(url);
      // 获取图片的宽度和高度
      Integer w = sourceImg.getWidth();
      Integer h = sourceImg.getHeight();
      if (w > WIDTH && h > HEIGHT) {
        return true;
      }
    } catch (Exception e) {
      log.info("获取图片失败");
    }
    return false;
  }
}