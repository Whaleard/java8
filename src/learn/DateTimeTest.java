package learn;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author Mr MC
 */
public class DateTimeTest {

    /**
     * LocalDate LocalTime LocalDateTime
     */
    @Test
    public void test01() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusMonths(2);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    /**
     * Instant：时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值）
     */
    @Test
    public void test02() {
        // 默认获取UTC时区
        Instant ins = Instant.now();
        System.out.println(ins);

        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);
        ZonedDateTime zdt = ins.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zdt);

        System.out.println(ins.toEpochMilli());

        Instant ins2 = Instant.ofEpochSecond(1);
        System.out.println(ins2);
    }

    /**
     * Duration：计算两个“时间”之间的间隔
     * Period：计算两个“日期”之前的间隔
     */
    @Test
    public void test03() {
        // 计算机使用的时间戳时间
        Instant ins = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins, ins2);
        System.out.println(duration.toMillis());

        // 人使用的时间
        LocalTime lt = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LocalTime lt2 = LocalTime.now();

        System.out.println(Duration.between(lt, lt2).toMillis());


        LocalDate ld = LocalDate.of(2015, 1, 1);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld, ld2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     * TemporalAdjuster：时间校正器
     */
    @Test
    public void test04() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        // 自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if (DayOfWeek.FRIDAY.equals(dow)) {
                return ldt4.plusDays(3);
            } else if (DayOfWeek.SATURDAY.equals(dow)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    /**
     * DateTimeFormatter：格式化时间/日期
     */
    @Test
    public void test05() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        // 格式化为想要的日期格式字符串
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        // 将字符串解析回时间日期
        LocalDateTime ldt3 = LocalDateTime.parse(strDate2, dtf2);
        System.out.println(ldt3);
    }

    /**
     * ZonedDate、ZonedTime、ZonedDateTime
     */
    @Test
    public void test06() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);

        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Aden"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Aden"));
        System.out.println(zdt);
    }
}
