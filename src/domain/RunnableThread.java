package domain;

/**
 * @ClassName RunnableThread
 * @Description 实现Runnable接口的方式实现多线程
 * @Author Mr.MC
 * @Date 2024/3/11 23:10
 * @Version 1.0
 **/
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("这是实现Runnable接口的线程");
        }
    }
}
