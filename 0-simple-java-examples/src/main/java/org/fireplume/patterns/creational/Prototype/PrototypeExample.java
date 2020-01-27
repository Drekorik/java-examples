package org.fireplume.patterns.creational.Prototype;

/**
 * Created by cloudjumper on 8/14/16.
 */
public class PrototypeExample {
    public static void main(String... args) throws CloneNotSupportedException {
        Cookie prototype = new CoconutCookie();
        prototype.weight = 3;
        CookieMachine cookieMachine = new CookieMachine(prototype);
        Cookie newCookie1 = cookieMachine.makeCookie();
        Cookie newCookie2 = cookieMachine.makeCookie();
//        newCookie2.weight=2;
        System.out.println(newCookie1.weight + " " + newCookie2.weight);
    }
}

abstract class Cookie implements Cloneable {
    protected int weight = 0;

    @Override
    protected Cookie clone() throws CloneNotSupportedException {
        return (Cookie) super.clone();
    }
}

class CoconutCookie extends Cookie {

}

class CookieMachine {
    private Cookie cookie;

    public CookieMachine(Cookie cookie) {
        this.cookie = cookie;

    }

    public Cookie makeCookie() throws CloneNotSupportedException {
        return this.cookie.clone();
    }
}
