package org.fireplume.patterns.creational.Singleton;

/**
 * Created by cloudjumper on 8/15/16.
 */
public class SingletonExample {
    public static void main(String... args) {
        Singleton.instanceOf().setData(5);
        System.out.println(Singleton.instanceOf().getData());
    }
}

class Singleton {
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private static Singleton instance = null;

    public static synchronized Singleton instanceOf() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    private Singleton() {

    }
}
