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
                System.out.println("����һ");
                break;
            case TUE:
                System.out.println("���ڶ�");
                break;
            case WED:
                System.out.println("������");
                break;
            default:
                System.out.println(test);
                break;
        }
        System.out.println(WeekEnum.getNameByIndex(2));
        System.out.println(test.name());
        // ��ȡö�ٳ�����ö�������е�λ�ã���ʼ����Ϊ0
        System.out.println(test.ordinal());
        for (WeekEnum weekEnum : WeekEnum.values()) {
            // toString()��name()������name����
            // Enum.valueOf(WeekEnum.class, weekEnum.toString());
            // ����ָ�����Ƶ�ö�����͵�ö�ٳ���
            WeekEnum weekEnum1 = Enum.valueOf(WeekEnum.class, weekEnum.name());
            System.out.println(weekEnum1);
        }
    }
}
