import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Doc {
    private B record = new B();
    private List<C> CRecords;

    Doc(){
        CRecords = new ArrayList<C>();
        CRecords.add(new C());
        CRecords.add(new C());
    }

    public B getRecord(){
        return record;
    }

    public List<C> getCRecords() {
        return CRecords;
    }
}

class A {
    public void call() {
        System.out.println("A");
    }
}

class B extends A {
    public void call1() {
        System.out.println("B");
    }
}

class C extends A {
    private static int counter = 0;
    private int myNumber;
    C() {
        myNumber = counter++;
    }
    public void call2() {
        System.out.println("C" + myNumber);
    }
}

public class Main {

    @SuppressWarnings("unchecked")
    private <T extends A> List<T> getDocObjects(Class<T> cls, Doc doc) {
        List<T> arr = new ArrayList<T>();
        if (cls == B.class) {
            arr.add((T)doc.getRecord());
        }
        else if (cls == C.class) {
            for (C obj: doc.getCRecords()) {
                arr.add((T)obj);
            }
        }
        return arr;
    }

    private <T> Method findMethod(Class<T> cls, String methodName) {
        if (cls == null) {
            return null;
        }
        for (Method m: cls.getDeclaredMethods()){
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return findMethod(cls.getSuperclass(), methodName);
    }

    private <T extends A> void invokeCallMethod(Doc doc, Class<T> cls, String methodName) {
        Method foundMethod = findMethod(cls, methodName);
        if (foundMethod == null) {
            throw new RuntimeException("Ha-Ha. No such method in given class (and it's ancestors)");
        }
        for (T obj: getDocObjects(cls, doc)){
            try {
                foundMethod.invoke(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Doc doc = new Doc();

        main.invokeCallMethod(doc, B.class, "aaa");
    }
}
