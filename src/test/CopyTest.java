package test;

import com.alibaba.fastjson.JSON;
import domain.Animal;
import domain.People;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Mr.MC
 */
public class CopyTest {

    /**
     * org.apache.commons.beanutils���º�org.springframework.beans���µ�
     * BeanUtils�ĸ��ƶ���ǳ����
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException {
        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i1.equals(i2));

        Class<? extends Integer> aClass = i1.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }

        People sourcePeople = new People("����", '��', new Animal("��ʿ��", "�ڰ�"));
        People targetPeople = new People();
        BeanUtils.copyProperties(targetPeople, sourcePeople);
        // targetPeople.setAnimal(new Animal("��ë", "�ٻ�"));
        targetPeople.getAnimal().setName("��ë");
        targetPeople.getAnimal().setColor("�ٻ�");
        System.out.println(sourcePeople);
        System.out.println(targetPeople);

        // People holomogyPeople = sourcePeople;
        // holomogyPeople.setAnimal(new Animal("��ë", "�ٻ�"));
        // System.out.println(sourcePeople);
        // System.out.println(holomogyPeople);
    }

    /**
     * lang3�µ�SerializationUtils�����
     */
    @Test
    public void test2() {
        People sourcePeople = new People("����", '��', new Animal("��ʿ��", "�ڰ�"));
        People targetPeople = SerializationUtils.clone(sourcePeople);
        sourcePeople.getAnimal().setName("��ë");
        sourcePeople.getAnimal().setColor("�ٻ�");
        System.out.println(sourcePeople);
        System.out.println(targetPeople);
    }

    /**
     * fastjson�����
     */
    @Test
    public void test3() {
        People sourcePeople = new People("����", '��', new Animal("��ʿ��", "�ڰ�"));
        String json = JSON.toJSONString(sourcePeople);
        People targetPeople = JSON.parseObject(json, People.class);
        sourcePeople.getAnimal().setName("��ë");
        sourcePeople.getAnimal().setColor("�ٻ�");
        System.out.println(sourcePeople);
        System.out.println(targetPeople);
    }

    @Test
    public void test() {
        String s1 = "2023-05-03";
        String s2 = "2023-05-10";
        System.out.println(s1.compareTo(s2) < 0);
    }
}
