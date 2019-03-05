import java.lang.ref.SoftReference;

public class Main {

    public static void main(String[] args) throws Exception {
        while (true) {
            Thread.sleep(1000);
        }

    }
    public boolean checkPossibility(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] < nums[i]) {
                    if(j - i > 1) {
                        return false;
                    }
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return true;
    }
    public int romanToInt(String s) {
        char[] nums = s.toCharArray();
        int rev = 0;
        for(int i = 0; i < nums.length; i++) {
            int val = 0;
            if(nums[i] == 'I') {
                val = 1;
                if(i + 1 < nums.length && nums[i + 1] == 'V') {
                    val = 4;
                    i++;
                }
                if(i + 1 < nums.length && nums[i + 1] == 'X') {
                    val = 9;
                    i++;
                }
            } else if(nums[i] == 'V') {
                val = 5;
            } else if(nums[i] == 'X') {
                val = 10;
                if(i + 1 < nums.length && nums[i + 1] == 'L') {
                    val = 40;
                    i++;
                }
                if(i + 1 < nums.length && nums[i + 1] == 'C') {
                    val = 90;
                    i++;
                }
            } else if(nums[i] == 'L') {
                val = 50;
            } else if(nums[i] == 'C') {
                val = 100;
                if(i + 1 < nums.length && nums[i + 1] == 'D') {
                    val = 400;
                    i++;
                }
                if(i + 1 < nums.length && nums[i + 1] == 'M') {
                    val = 900;
                    i++;
                }
            } else if(nums[i] == 'D') {
                val = 500;
            } else if(nums[i] == 'M') {
                val = 1000;
            }
            rev = rev + val;
        }
        return rev;
    }
    public boolean isPalindrome(int x) {
        if(x == 0) {
            return true;
        }
        if(x < 0 || x % 10 == 0) {
            return false;
        }

        int temp = x;
        int result = 0;
        while(x != 0) {
            result = result * 10 + x % 10;
            if(result == temp) {
                return true;
            }
            x = x / 10;
        }

        return false;
    }
    public int reverse(int x) {

        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if(result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE / 10 && pop > 7) {
                return 0;
            }
            if(result < Integer.MIN_VALUE / 10 || result == Integer.MIN_VALUE / 10 && pop < -8) {
                return 0;
            }
            result = result * 10 + pop;

        }
        return result;
    }

    public static SoftReference<User> testWeakReference() {
        User user = new User("hello", "123");

        SoftReference<User> userWeakReference = new SoftReference<User>(user);
        System.out.println(userWeakReference.get());
        //另一种方式触发GC，强制执行GC
        System.gc();
        System.runFinalization();
        System.out.println(userWeakReference.get());
        return userWeakReference;
    }

    public static class User {
        private String userName;
        private String userPwd;

        public User(String userName, String userPwd) {
            this.userName = userName;
            this.userPwd = userPwd;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserPwd() {
            return userPwd;
        }
    }
}
