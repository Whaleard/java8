package thread;

/**
 * synchronized只能用来修饰方法或者代码块
 */
public class Count {

    public int num = 0;

    private static byte[] lock = new byte[1];

    public void method() {
        try {
            Thread.sleep(51);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        num += 1;
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }

    /**
     * 修饰在方法体上的synchronized默认锁的对象就是当前对象本身
     */
    public synchronized void method2() {
        try {
            Thread.sleep(51);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }

    public void method3() {
        synchronized (this) {   // this此时理解只有相同对象访问时才会被阻塞，即该对象在系统中。不同对象访问时不会阻塞
            try {
                Thread.sleep(51);
            } catch (InterruptedException e) {

            }
            num++;
            System.out.println(Thread.currentThread().getName() + "-" + num);
        }
    }

    /**
     * synchronized(static)
     */
    public void method4() {
        // lock是静态变量在内存中只有一个
        synchronized (lock) {

        }
    }

    /**
     * synchronized(A.class)锁类，
     */
    public void method5() {
        synchronized (Count.class) {
            // 类名.class代表的是该类的Class对象。每个类在被编译后都会在内存中生成一个Class对象。
            // Class<Count> countClass = Count.class;
        }
    }
}
