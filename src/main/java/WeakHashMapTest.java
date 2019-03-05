import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] agrs) throws InterruptedException {
        WeakHashMap<String, Integer> weakHashMap = new WeakHashMap<String, Integer>();
        String key = new String("1");
        String key2 = new String("2");
        Integer value = 123;
        weakHashMap.put(key, value);
        weakHashMap.put(key2, 234);
        System.out.println(weakHashMap.get(key));
        key = null;
        System.gc();
        Thread.sleep(2000);
        System.out.println(weakHashMap.size());
        //System.out.println(weakHashMap.get(key));
    }

}
