package test;

import domain.Animal;
import domain.MyThread;
import domain.Station;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mr.MC
 */
public class ThreadTest {

    // public static void main(String[] args) {
    //     Station s1 = new Station("窗口1");
    //     Station s2 = new Station("窗口2");
    //     Station s3 = new Station("窗口3");
    //
    //     s1.start();
    //     s2.start();
    //     s3.start();
    // }

    @Test
    public void test01() throws InterruptedException {
        // 任务执行时间监视器
        StopWatch stopWatch = new StopWatch();
        // 开始计时
        stopWatch.start();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    StopWatch stopWatch2 = new StopWatch();
                    stopWatch2.start();
                    Thread.sleep(3000);
                    stopWatch2.stop();
                    System.out.println(stopWatch2.getTime());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread.sleep(1000);
        runnable.run();
        // 停止计时
        stopWatch.stop();
        // 统计从start到现在的计时
        System.out.println(stopWatch.getTime());
    }

    @Test
    public void test03() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("1");
            }
        });
        t1.run();
        // t1.start();

        MyThread t2 = new MyThread();
        t2.run();
    }
}
