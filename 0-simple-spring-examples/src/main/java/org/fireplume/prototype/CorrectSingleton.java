package org.fireplume.prototype;

public abstract class CorrectSingleton implements Singleton {
    private Prototype prototype;

    public abstract Prototype getPrototype();

    @Override
    public void printPrototype() {
        prototype = getPrototype();
        System.out.println(prototype);
    }
}
