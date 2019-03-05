public class SynchronizedTest {
    public static SynchronizedTest object = new SynchronizedTest();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (SynchronizedTest.class) {
                    System.out.println("class lock");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("class lock end");
                }
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("start object lock");
        synchronized (object) {
            System.out.println("object lock");
        }

    }
}
