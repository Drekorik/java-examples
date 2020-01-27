package org.fireplume.java11;

public class Main {
    public static void main(String[] args) {
        I1.anyStaticMethod1();
        var var1 = new I1() {
            @Override
            public void anyMethod1() {
                System.out.println("Any method 1");
            }
        };
        var1.anyMethod1();
        var1.anyMethod2();
    }

    // Private methods in interfaces
    private interface I1 {

        void anyMethod1();

        default void anyMethod2() {
            System.out.println("Any method 2");
            thisIsPrivateMethod();
        }

        private void thisIsPrivateMethod() {
            System.out.println("This is private method");
        }

        static void anyStaticMethod1() {
            System.out.println("Any static method 1");
            thisIsPrivateStaticMethod();
        }

        private static void thisIsPrivateStaticMethod() {
            System.out.println("This is private static method");
        }
    }
}
