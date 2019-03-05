import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        String key = new String("6");
        Integer val = new Integer(66666);
        Container container = new Container();
        ReferenceQueue<Object> r = new ReferenceQueue<Object>();

        Entry<String, Integer> weakReference = new Entry<String, Integer>(key, val, r);
        container.setEntry(weakReference);

        key = null;
        weakReference = null;

        System.out.println(container.getEntry().val);

        System.gc();

        Thread.sleep(1000);

        System.out.println("------------");

        Reference ret = r.poll();
        while (ret != null) {
            System.out.println("queued value " + ((Entry) ret).val);
            ret = r.poll();
        }

        System.out.println("------------");

        System.out.println(container.getEntry().val);
    }


    static class Container {
        private Entry<String, Integer> entry;

        public void setEntry(Entry<String, Integer> entry) {
            this.entry = entry;
        }

        public Entry<String, Integer> getEntry() {
            return entry;
        }
    }
    static class Entry<K, V> extends WeakReference<Object> {
        private V val;

        public Entry(K key, V val) {
            super(key);
            this.val = val;
        }

        public Entry(K key, V val, ReferenceQueue<? super Object> q) {
            super(key, q);
            this.val = val;
        }
    }
}
