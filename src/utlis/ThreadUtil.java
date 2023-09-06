package utlis;

import java.util.concurrent.*;

/**
 * @author Mr.MC
 */
public class ThreadUtil {

    /**
     * newFixedThreadPool创建一个定长线程池，
     * 可控制线程最大并发数，超出的线程会在队列中等待。
     *
     * @param var0
     * @return
     */
    public static ExecutorService newFixedThreadPool(int var0) {
        return new ThreadPoolExecutor(var0, var0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    /**
     * newSingleThreadExecutor 创建一个单线程化的线程池，
     * 它只会唯一的工作线程来执行任务，保证所有任务按照指定顺序（FIFO，LIFO，优先级）执行。
     *
     * @return
     */
    public static ExecutorService newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * newCachedThreadPool创建一个可缓存的线程池，
     * 如果线程池长度超过处理需求，可灵活回收空闲线程，
     * 若无可回收，则新建线程。
     *
     * @return
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    /**
     * newScheduledThreadPool创建一个定长线程池，
     * 支持定时及周期性任务执行。
     *
     * @param var0
     * @return
     */
    public static ScheduledExecutorService newScheduledThreadPool(int var0) {
        return new ScheduledThreadPoolExecutor(var0);
    }
}
