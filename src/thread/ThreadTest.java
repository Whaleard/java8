package thread;

import base.ServiceSupport;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.MC
 */
public class ThreadTest extends ServiceSupport {

    // 可重入锁ReentrantLock
    public static ReentrantLock lock = new ReentrantLock();

    /**
     * volatile关键字修饰的变量，读会从内存中读取，写不会立即写入内存
     **/
    public volatile static int volCount = 0;

    /**
     * 1、通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
     */
    private static ThreadLocal<Integer> initValue = new ThreadLocal<Integer>() {
        @Override
        // 初始化变量
        public Integer initialValue() {
            return 0;
        }
    };

    /**
     * 创建ThreadLocal变量并初始化（替代通过new创建对象并重写initialValue()方法）
     */
    private ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

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
        Runnable runnable = () -> {
            try {
                StopWatch stopWatch2 = new StopWatch();
                stopWatch2.start();
                Thread.sleep(3000);
                stopWatch2.stop();
                System.out.println(stopWatch2.getTime());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
        ThreadTest tt = new ThreadTest();
        // 3、3个线程共享tt，各自产生序列号
        ThreadClient t1 = new ThreadClient(tt);
        ThreadClient t2 = new ThreadClient(tt);
        ThreadClient t3 = new ThreadClient(tt);
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 线程处理unchecked exception
     **/
    @Test
    public void test07() {
        ExceptionThread task = new ExceptionThread();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandlerThread());
        thread.start();
    }

    /**
     * 原子操作automic
     * todo 这里待学习cpu的比较并交换和非阻塞算法
     */
    @Test
    public void test08() {
        AtomicInteger ai = new AtomicInteger(0);
        // 获取当前值
        System.out.println(ai.get());
        // 获取当前的值，并设置新值
        System.out.println(ai.getAndSet(5));
        // 获取当前的值，并自增
        System.out.println(ai.getAndIncrement());
        // 获取当前的值，并自减
        System.out.println(ai.getAndDecrement());
        // 获取当前的值，并加上预期的值
        System.out.println(ai.getAndAdd(10));
        System.out.println(ai.get());
    }

    /**
     * synchronize和ReentrantLock都是可重入锁
     */
    @Test
    public void test09() {
        lock1();
    }

    /**
     * 单例共享变量导致线程安全问题
     */
    @Test
    public void test10() {
        Count count = new Count();
        for (int i = 0; i < 5; i++) {
            CountThread task = new CountThread(count);
            task.start();
        }
        try {
            Thread.sleep(1001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5个人干完活：最后的值" + count.num);
    }

    public ThreadLocal<Integer> getThreadLocal() {
        return seqNum;
    }

    /**
     * TimeUnit
     *      1、可以进行时间颗粒度转换：toSeconds(long d)
     *      2、延时：TimeUnit.MICROSECONDS.sleep(long timeout)
     */
    @Test
    public void test11() {
        Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                logger.info(Thread.currentThread().getName() + "获取了锁");

                try {
                    logger.info(Thread.currentThread().getName() + "休眠一会儿");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                logger.info(Thread.currentThread().getName() + "调用wait...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                logger.info(Thread.currentThread().getName() + "被唤醒");
            }
        }, "A");

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock.notify();
    }

    @Test
    public void test12() {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                logger.info(Thread.currentThread().getName() + "获取了锁");
                try {
                    logger.info(Thread.currentThread().getName() + "休眠一会儿");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logger.info(Thread.currentThread().getName() + "调用wait...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                logger.info(Thread.currentThread().getName() + "被唤醒");
            }
        }, "A");

        thread.start();

        // try {
        //     TimeUnit.SECONDS.sleep(5);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                logger.info(Thread.currentThread().getName() + "获取了锁");

                logger.info(Thread.currentThread().getName() + "叫醒thread");
                lock.notify();
            }
        }, "B");

        thread2.start();
    }

    @Test
    public void test13() {
        ExecutorService executor = ThreadUtil.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("into" + no);
                        Thread.sleep(1000);
                        System.out.println("end" + no);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            // lambda简化写法
            // Runnable runnable = () -> {
            //     try {
            //         System.out.println("into" + no);
            //         Thread.sleep(1000);
            //         System.out.println("end" + no);
            //     } catch (InterruptedException e) {
            //         throw new RuntimeException(e);
            //     }
            // };
            executor.execute(runnable);
        }
        executor.shutdown();
        System.out.println("Thread Main End!");
    }

    /**
     * 2、获取下一个序列值
     * @return
     */
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    private static class ThreadClient extends Thread {
        private ThreadTest tt;

        public ThreadClient(ThreadTest tt) {
            this.tt = tt;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                // 4、每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName()
                        + "] --> tt[" + tt.getNextNum() + "]");
            }

            tt.getThreadLocal().remove();
        }
    }

    public void lock1() {
        try {
            lock.lock();
            logger.info("------lock1执行------");
            lock2();
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void lock2() {
        try {
            lock.lock();
            logger.info("------lock2执行------");
        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
