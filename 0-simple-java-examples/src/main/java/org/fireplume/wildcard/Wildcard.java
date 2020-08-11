package org.fireplume.wildcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Wildcard {
    public static void main(String[] args) {
        {
            List<String> listOfStrings = new ArrayList<>();
            listOfStrings.add("Any");
            // ? means absolutely anything, so we can not add, but only read
            List<?> wildList = listOfStrings;
            // wildList.add("Any other string"); - won't compile
            Object o = wildList.get(0);
        }

        {
            // ? extends T means objects that are instances of the class A, or subclasses of A
            List<? extends A> aList = Arrays.asList(new A(), new B(), new C());
            // aList.add(new A()); - still won't compile
            // But we can work with elements as with A
            A a = aList.get(0);

            List<? extends A> bList = new ArrayList<B>();
            List<? extends A> cList = new ArrayList<C>();

            // You can't add any object to List<? extends T> because you can't guarantee
            // what kind of List it is really pointing to,
            // so you can't guarantee that the object is allowed in that List.
            // The only "guarantee" is that you can only read from it and you'll get a T or subclass of T
        }

        {
            // ? super T means that the list is typed to either the A class, or a superclass of A
            List<? super A> aList = new ArrayList<A>();
            // When you know that the list is typed to either A, or a superclass of A,
            // it is safe to insert instances of A or subclasses of A
            aList.add(new A());
            aList.add(new B());
            aList.add(new C());
            // However, since any class eventually subclass Object you can read objects from the list
            // if you cast them to Object
            Object object = aList.get(0);

            List<? super B> aListAgain = new ArrayList<A>();
            List<? super C> aListAgainAndAgain = new ArrayList<A>();
            List<? super C> bList = new ArrayList<B>();
            List<? super C> ObjectsList = new ArrayList<Object>();

            // We can not guarantee what objects inside list,
            // we can only be sure that they are superclasses or subclasses
        }
    }

    private static class A {

    }

    private static class B extends A {

    }

    private static class C extends B {

    }
}
