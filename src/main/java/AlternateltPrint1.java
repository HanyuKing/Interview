import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数组A内容为 1,2,3,4...52 ,数组B内容为26个英文字母，使用两个线程分别输入两个数组，
 *         打印内容为：12a34b56c78e....... 这样的规律
 */
public class AlternateltPrint1 {

    static final Lock lock = new ReentrantLock();
    static final Condition condition1 = lock.newCondition();
    static final Condition condition2 = lock.newCondition();


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 1; i <= 52; i += 2) {
                    lock.lock();
                    try {
                        System.out.print(i);
                        System.out.print(i + 1);
                        condition2.signal();
                        condition1.await();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 'a'; i < 'z'; i++) {
                    lock.lock();
                    try {
                        System.out.print((char) i );
                        condition1.signal();
                        condition2.await();
                    } catch (Exception e) {

                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }
}
