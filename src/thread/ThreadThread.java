package thread;

/**
 * 继承Thread类的方式实现多线程
 * @author issuser
 */
public class ThreadThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(this.getName() + "：Hello");
        }
    }
}
