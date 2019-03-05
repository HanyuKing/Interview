public class Thread2 {
    static volatile boolean isMain = true;
    static final Object mutex = new Object();
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 50; i++) {
                    synchronized (mutex) {
                        while (isMain) {
                            try {
                                mutex.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("sub");
                        isMain = true;
                        mutex.notify();
                    }
                }
            }
        }).start();

        for(int i = 0; i < 50; i++) {
            synchronized (mutex) {
                while (!isMain) {
                    mutex.wait();
                }
                System.out.println("main");
                isMain = false;
                mutex.notify();
            }
        }
    }
}
