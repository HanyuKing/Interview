import java.lang.ref.WeakReference;

public class FinalizeCase {

    private static Block holder = null;

    public static void main(String[] args) throws Exception {
        holder = new Block();
        holder = null;
        System.gc();


        //System.in.read();
    }
    static class Block {
        byte[] _200M = new byte[2];

        @Override
        protected void finalize() throws Throwable {
            System.out.println("123");
        }
    }

}