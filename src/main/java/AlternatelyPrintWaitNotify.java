

public class AlternatelyPrintWaitNotify {

    static Object mutex = new Object();
    static volatile boolean isA = true;
    static volatile int count = 0;

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (mutex) {
                        while (!isA) {
                            try {
                                mutex.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("A" + ++count);
                        mutex.notify();
                        isA = false;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (mutex) {
                        while (isA) {
                            try {
                                mutex.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("B" + ++count);
                        mutex.notify();
                        isA = true;
                    }
                }
            }
        }).start();
    }
}
