import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.Test;
import java.util.Date;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
public class DateTest {

  @Test
  public void testLocalDateTime() {
    LocalDate d = LocalDate.now(); // 当前日期
    LocalTime t = LocalTime.now(); // 当前时间
    LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间

    System.out.println(d); // 严格按照ISO 8601格式打印 2021-07-20
    System.out.println(t); // 严格按照ISO 8601格式打印 15:42:59.069074268
    System.out.println(dt); // 严格按照ISO 8601格式打印
    System.out.println(new Date()); // Tue Jul 20 23:48:03 CST 2021
  }
}
