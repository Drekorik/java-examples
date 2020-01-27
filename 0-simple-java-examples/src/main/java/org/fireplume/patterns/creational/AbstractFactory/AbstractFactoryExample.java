package org.fireplume.patterns.creational.AbstractFactory;

/**
 * Created by cloudjumper on 8/14/16.
 */
public class AbstractFactoryExample {
    public static void main(String... args) {
        AbstractFactory factory = new Factory();
        AbstractProduct p1 = factory.createProduct1();
        AbstractProduct p2 = factory.createProduct2();
        p1.exec("Hello");
        p2.exec("World");
        p1.exec("!!!");
    }
}

interface AbstractProduct {
    void exec(String msg);
}

interface AbstractFactory {
    AbstractProduct createProduct1();

    AbstractProduct createProduct2();
}

class Product1 implements AbstractProduct {
    @Override
    public void exec(String msg) {
        System.out.println("AbstractProduct 1 -> " + msg);
    }
}

class Product2 implements AbstractProduct {
    @Override
    public void exec(String msg) {
        System.out.println("AbstractProduct 2 -> " + msg);
    }
}

class Factory implements AbstractFactory {
    @Override
    public Product1 createProduct1() {
        return new Product1();
    }

    @Override
    public Product2 createProduct2() {
        return new Product2();
    }
}
