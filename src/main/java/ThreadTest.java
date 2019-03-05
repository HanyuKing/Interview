public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(new A().getA() == new A().getA());
    }
}
class A {
    private String name = "A";
    public void print() {
        System.out.println(name.hashCode());
    }
    public String getA() {
        return name;
    }
}
