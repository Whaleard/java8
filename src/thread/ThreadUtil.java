package thread;

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
        /*
            以单线程线程池为例：
                public static ExecutorService newSingleThreadExecutor() {
                    return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
                }

                ThreadPoolExecutor构造函数参数详解：
                    corePoolSize：核心池大小，核心池中的线程会一直存活不会被销毁。当有新任务时，若线程池中线程数小于核心线程数，线程池会创建一个线程去处理。
                    maximumPoolSize：当线程池中的线程数大于corePoolSize且小于maximumPoolSize时，动态创建与回收线程池中的线程资源。

         */
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
