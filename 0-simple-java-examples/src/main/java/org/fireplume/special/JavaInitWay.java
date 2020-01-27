package org.fireplume.special;

public class JavaInitWay {
    public static void main(String[] args) {
        new B();
    }
}

class A {
    private Integer a = 1;

    public A() {
        someMethod();
    }

    protected void someMethod() {
        System.out.println("This is A and: " + a);
    }
}

class B extends A {
    private Integer a = 2;

    public B() {
        someMethod();
    }

    @Override
    public void someMethod() {
        System.out.println("This is B and: " + a);
    }
}
