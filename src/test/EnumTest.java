package test;

import constant.WeekEnum;
import org.junit.Test;

/**
 * @author Mr.MC
 */
public class EnumTest {

    @Test
    public void test01() {
        WeekEnum test = WeekEnum.TUE;
        switch (test) {
            case MON:
                System.out.println("星期一");
                break;
            case TUE:
                System.out.println("星期二");
                break;
            case WED:
                System.out.println("星期三");
                break;
            default:
                System.out.println(test);
                break;
        }
        System.out.println(WeekEnum.getNameByIndex(2));
        System.out.println(test.name());
        // 获取枚举常量在枚举声明中的位置，初始索引为0
        System.out.println(test.ordinal());
        for (WeekEnum weekEnum : WeekEnum.values()) {
            // toString()和name()均返回name属性
            // Enum.valueOf(WeekEnum.class, weekEnum.toString());
            // 返回指定名称的枚举类型的枚举常量
            WeekEnum weekEnum1 = Enum.valueOf(WeekEnum.class, weekEnum.name());
            System.out.println(weekEnum1);
        }
    }
}
