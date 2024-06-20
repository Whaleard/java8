package thread;

public class CountThread extends Thread {
    private Count count;

    public CountThread(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        // count.method();
        count.method2();
    }
}
