import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class MethodAreaOOM extends Number {

    public static void main(String[] args) {
        int count = 0;
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setClassLoader(new MyClassLoader());
            enhancer.setSuperclass(MethodAreaOOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args);
                }
            });
            enhancer.create();
            System.out.println(++count);
        }
    }

    public static class MyClassLoader extends ClassLoader {
        public MyClassLoader(){};

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }
    }

    public int intValue() {
        return 0;
    }

    public long longValue() {
        return 0;
    }

    public float floatValue() {
        return 0;
    }

    public double doubleValue() {
        return 0;
    }
}