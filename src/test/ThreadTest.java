package test;

import domain.*;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
    public void test02() {
        ThreadThread t1 = new ThreadThread();
        // 启动继承Thread类的线程方式
        t1.start();

        RunnableThread t2 = new RunnableThread();
        // 启动实现Runnable接口的线程方式
        new Thread(t2).start();

        CallableThread t3 = new CallableThread();
        FutureTask<String> futureTask = new FutureTask<>(t3);
        // 启动实现Callable接口的线程方式
        new Thread(futureTask).start();
        try {
            System.out.println("Callable接口得到的返回结果是：" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果不以start()方法启动线程，而是直接调用run()方法，则调用run()方法的线程执行相应操作，而非新创建线程执行相应操作
     **/
    @Test
    public void test03() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("1");
            }
        });

        // run()方法只是Thread类中的一个普通方法，在主线程里执行
        // run()方法必须是public的访问权限，返回类型为void
        // t1.run();
        // start()方法用来启动相应的线程
        // 需要并行处理的代码放在run()方法中，start()方法启动线程后自动调用run()方法
        // 同一个线程对象只能调用一次start()方法，调用两次会抛出IllegalThreadStateException异常
        t1.start();

        ThreadThread t2 = new ThreadThread();
        // t2.run();
        t2.start();
    }

    // @Test
    // public void test04() {
    //     Thread t = new Thread();
    //     t.start();
    //     // 只有线程启动之后才会
    //     System.out.println(Thread.activeCount());
    // }

    /**
     * 当前进程结束，守护线程随之结束
     **/
    @Test
    public void test05() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("线程1第" + i + "次执行！");
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 9999999999L; i++) {
                System.out.println("线程2第" + i + "次执行！");
            }
        });

        // 设置为守护线程，注意一定要在线程开始（调用start()方法）之前调用
        t2.setDaemon(true);

        t1.start();
        t2.start();
    }

    @Test
    public void test06() {
        
    }
}
