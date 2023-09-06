package domain;

/**
 * @author Mr.MC
 */
public class Station extends Thread {

    public Station(String name) {
        super(name);
    }

    static int ticket = 5;

    static Object obj = "aa";

    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (obj) {
                if (ticket > 0) {
                    System.out.println(getName() + "卖出了第" + ticket + "张票");
                    ticket--;
                } else {
                    System.out.println("票卖完了");
                }
            }
        }
    }
}
