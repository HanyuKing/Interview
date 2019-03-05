import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {
    Object obj = new Object();
    public static void main(String[] args) {
        FinalizerClass A = new FinalizerClass("A");
        MyPhantomReference<FinalizerClass> PA = new MyPhantomReference<FinalizerClass>(A);
        FinalizerClass B = new FinalizerClass("B");
        MyPhantomReference<FinalizerClass> PB = new MyPhantomReference<FinalizerClass>(B);
        PA.setFinalizerClass(B);

        System.out.println(PA.isEnqueued());

        A = null;

        System.out.println(PA.isEnqueued());
        System.gc();
        System.out.println(PA.isEnqueued());
    }

    static class FinalizerClass {
        private String val;

        public FinalizerClass(String val){
            this.val = val;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.val);
        }
    }
    static class MyPhantomReference<T> extends PhantomReference {
        private FinalizerClass finalizerClass;

        /**
         * Creates a new phantom reference that refers to the given object and
         * is registered with the given queue.
         *
         * <p> It is possible to create a phantom reference with a <tt>null</tt>
         * queue, but such a reference is completely useless: Its <tt>get</tt>
         * method will always return null and, since it does not have a queue, it
         * will never be enqueued.
         *
         * @param referent the object the new phantom reference will refer to
         * @param q        the queue with which the reference is to be registered,
         */
        public MyPhantomReference(T referent, ReferenceQueue q) {
            super(referent, q);
        }
        public MyPhantomReference(T referent) {
            super(referent, null);
        }

        public void setFinalizerClass(FinalizerClass finalizerClass) {
            this.finalizerClass = finalizerClass;
        }

        public FinalizerClass getFinalizerClass() {
            return finalizerClass;
        }
    }
}
