package domain;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class Son extends Father{
    private Life life;

    @Override
    public Life getLife() {
        return life;
    }

    @Override
    public void setLife(Life life) {
        this.life = life;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Father cuishan = new Father();
        cuishan.setFace("handsome");
        cuishan.setHeight("180");
        Life cuishanLife = new Life();
        cuishanLife.setStatus("alive");
        cuishan.setLife(cuishanLife);
        Son wuji=new Son();
        BeanUtils.copyProperties(wuji,cuishan);

        System.out.println(wuji.getLife());
        System.out.println(cuishan.getLife());

        Life wujiLife = wuji.getLife();
        wujiLife.setStatus("alive");
        wuji.setLife(wujiLife);
        cuishanLife.setStatus("dead"); // 翠山后来自刎了

        System.out.println(cuishan);
        System.out.println(wuji);
    }
}
