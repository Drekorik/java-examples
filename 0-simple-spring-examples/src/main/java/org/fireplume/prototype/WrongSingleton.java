package org.fireplume.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WrongSingleton implements Singleton {
    @Autowired
    private Prototype prototype;

    @Override
    public void printPrototype() {
        System.out.println(prototype);
    }
}
