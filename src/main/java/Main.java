import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Doc {
    private B bRecord = new B();
    private List<C> cRecords;

    Doc(){
        cRecords = new ArrayList<C>();
        cRecords.add(new C());
        cRecords.add(new C());
    }

    public B getBRecord(){
        return bRecord;
    }

    public List<C> getCRecords() {
        return cRecords;
    }
}

class A {
    protected String value = "A";

    public void setValue(String value) {
        this.value = value;
    }

    public void call() {
        System.out.println(value);
    }
}

class B extends A {
    B() {this.value = "B";}

    public void call_1() {
        System.out.println("B --- " + value);
    }
}

class C extends A {
    private static int counter = 0;
    private int myNumber;

    C() {
        this.value = "C";
        myNumber = counter++;
    }

    public void call_2() {
        System.out.println(value + myNumber);
    }

    public void setMyNumber(int value) {
        this.myNumber = value;
    }
}

public class Main {

    @SuppressWarnings("unchecked")
    private <T extends A> List<T> getDocObjects(Class<T> cls, Doc doc) {
        List<T> arr = new ArrayList<T>();
        if (cls == B.class) {
            arr.add((T)doc.getBRecord());
        }
        else if (cls == C.class) {
            for (C obj: doc.getCRecords()) {
                arr.add((T)obj);
            }
        }
        return arr;
    }

    private boolean checkTypesFit(Type[] methodTypes, Type[] expectedTypes) {
        if (methodTypes.length != expectedTypes.length) {
            return false;
        }
        for(int i=0; i < methodTypes.length; i++) {
            if(methodTypes[i] != expectedTypes[i]) {
                return false;
            }
        }
        return true;
    }

    private <T> Method findMethod(Class<T> cls, String methodName, Type... paramTypes) {
        if (cls == null) {
            return null;
        }
        for (Method m: cls.getDeclaredMethods()){
            if (m.getName().equals(methodName) && checkTypesFit(m.getParameterTypes(), paramTypes)) {
                return m;
            }
        }
        return findMethod(cls.getSuperclass(), methodName, paramTypes);
    }

    private <T extends A> void invokeCallMethod(Doc doc, Class<T> cls, String methodName, String newValue) {
        Method foundMethod = findMethod(cls, methodName, String.class);
        if (foundMethod == null) {
            throw new RuntimeException("Ha-Ha. No method with given params in given class (and it's ancestors)");
        }

        for (T obj: getDocObjects(cls, doc)){
            try {
                foundMethod.invoke(obj, newValue);
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

        doc.getBRecord().call_1();
        main.invokeCallMethod(doc, B.class, "setValue", "newValue");
        doc.getBRecord().call_1();
        main.invokeCallMethod(doc, B.class, "setMyNumber", "newValue");

    }
}
