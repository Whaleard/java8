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
     * org.apache.commons.beanutils包下和org.springframework.beans包下的
     * BeanUtils的复制都是浅拷贝
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

        People sourcePeople = new People("张三", '男', new Animal("哈士奇", "黑白"));
        People targetPeople = new People();
        BeanUtils.copyProperties(targetPeople, sourcePeople);
        // targetPeople.setAnimal(new Animal("金毛", "橘黄"));
        targetPeople.getAnimal().setName("金毛");
        targetPeople.getAnimal().setColor("橘黄");
        System.out.println(sourcePeople);
        System.out.println(targetPeople);

        // People holomogyPeople = sourcePeople;
        // holomogyPeople.setAnimal(new Animal("金毛", "橘黄"));
        // System.out.println(sourcePeople);
        // System.out.println(holomogyPeople);
    }

    /**
     * lang3下的SerializationUtils的深拷贝
     */
    @Test
    public void test2() {
        People sourcePeople = new People("张三", '男', new Animal("哈士奇", "黑白"));
        People targetPeople = SerializationUtils.clone(sourcePeople);
        sourcePeople.getAnimal().setName("金毛");
        sourcePeople.getAnimal().setColor("橘黄");
        System.out.println(sourcePeople);
        System.out.println(targetPeople);
    }

    /**
     * fastjson的深拷贝
     */
    @Test
    public void test3() {
        People sourcePeople = new People("张三", '男', new Animal("哈士奇", "黑白"));
        String json = JSON.toJSONString(sourcePeople);
        People targetPeople = JSON.parseObject(json, People.class);
        sourcePeople.getAnimal().setName("金毛");
        sourcePeople.getAnimal().setColor("橘黄");
        System.out.println(sourcePeople);
        System.out.println(targetPeople);
    }

    /**
     * java调用函数时的传参，传递的其实是参数的副本，也就是另一个指向该地址的引用。
     * 若是通过setXxx()来更新副本值，副本变则原地址内容变；
     * 若是通过new重新给该副本赋值，则副本指向新地址，不会改变原地址的内容。
     */
    @Test
    public void test4() {
        Animal animal = new Animal("二哈", "黑白");
        this.changeName(animal);
        System.out.println(animal);
    }

    private void changeName(Animal animal) {
        System.out.println(animal);
        // 通过setXxx()方法更改数据则入参数据值改变
        // animal.setName("大黄");

        // 重新给入参赋值，则不会更改原数据值
        animal = new Animal("大黄", "黑白");
    }
}
