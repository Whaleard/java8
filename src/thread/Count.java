package thread;

public class Count {

    public int num = 0;

    public synchronized void add() {
        try {
            Thread.sleep(51);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "-" + num);
    }
}
