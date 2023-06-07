package utlis;

import java.util.concurrent.*;

/**
 * @author Mr.MC
 */
public class ThreadUtil {

    /**
     * newFixedThreadPool����һ�������̳߳أ�
     * �ɿ����߳���󲢷������������̻߳��ڶ����еȴ���
     *
     * @param var0
     * @return
     */
    public static ExecutorService newFixedThreadPool(int var0) {
        return new ThreadPoolExecutor(var0, var0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    /**
     * newSingleThreadExecutor ����һ�����̻߳����̳߳أ�
     * ��ֻ��Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��FIFO��LIFO�����ȼ���ִ�С�
     *
     * @return
     */
    public static ExecutorService newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * newCachedThreadPool����һ���ɻ�����̳߳أ�
     * ����̳߳س��ȳ����������󣬿������տ����̣߳�
     * ���޿ɻ��գ����½��̡߳�
     *
     * @return
     */
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    /**
     * newScheduledThreadPool����һ�������̳߳أ�
     * ֧�ֶ�ʱ������������ִ�С�
     *
     * @param var0
     * @return
     */
    public static ScheduledExecutorService newScheduledThreadPool(int var0) {
        return new ScheduledThreadPoolExecutor(var0);
    }
}
