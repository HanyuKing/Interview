import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
    public static final int POOL_SIZE = 100;
    public static final int DATA_LOOP_SIZE = 10000;

    private static ThreadLocal<List<User>> threadLocalUsers = new ThreadLocal<List<User>>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
        for(int i = 0; i < POOL_SIZE; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    threadLocalUsers.set(addBigList());
                    System.out.println(Thread.currentThread().getName());

                }
            });
        }
        //threadLocalUsers = null;
        Thread.sleep(5000);
        System.gc();
        System.out.println(1000);
    }

    public static List<User> addBigList() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < DATA_LOOP_SIZE; i++) {
            users.add(new User("hanyu", "123", "1", 20));
        }
        return users;
    }

    static class User {
        private String userName;
        private String passwd;
        private String sex;
        private int age;

        public User(String userName, String passwd, String sex, int age) {
            this.userName = userName;
            this.passwd = passwd;
            this.sex = sex;
            this.age = age;
        }
    }
}
