package com.example.mytest.jsoup;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 测试jsoup.
 *
 * @author 邵益炯
 * @date 2018/7/31
 */
public class JsoupTest {

  private static final Integer WIDTH = 300;
  private static final Integer HEIGHT = 300;
  private static final String HTTP = "http:";
  private static final String DEFAULT_IMAGE = "http://pic1.win4000.com/wallpaper/3/59783bb506c6e.jpg";
  private static final String USER_AGENT = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";

  private static boolean getImgSize(String src) {
    BufferedImage sourceImg;
    try {
      URL url = new URL(src);
      // 根据url获取BufferImage对象
      sourceImg = ImageIO.read(url);
      // 获取图片的宽度和高度
      Integer w = sourceImg.getWidth();
      Integer h = sourceImg.getHeight();
      if (w > JsoupTest.WIDTH && h > JsoupTest.HEIGHT) {
        return true;
      }
    } catch (IOException e) {
      // TODO 打日志 而不是打控制台
      System.out.println("获取图片失败");
    }
    return false;
  }

  /**
   * 测试.
   */
  public static void main(String[] args) throws Exception {
    String html = "http://news.163.com/photoview/00AO0001/2295329.html#p=DO1KF9T400AO0001NOS";
    String html2 = "https://weibo.com/?category=novelty";
    //将请求模拟成手机访问
    Document doc = Jsoup.connect(html2).userAgent(USER_AGENT).timeout(3000).get();
    System.out.println(doc.title());
    Elements elements = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
    for (Element element : elements) {
      String src = element.attr("src");
      if (StringUtils.isNoneBlank(src)) {
        //有的url缺少http前缀
        if (!src.contains(HTTP)) {
          src = HTTP + src;
        }
        if (getImgSize(src)) {
          System.out.println("符合 " + src);
          return;
        }
      }
    }
    System.out.println("返回默认图片 " + DEFAULT_IMAGE);
  }


}
