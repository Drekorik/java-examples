package org.fireplume.patterns.structural.Adapter;

/**
 * Created by cloudjumper on 8/15/16.
 */
public class AdapterExample {
    public static void main(String... args) {
        Chief chief = new ChiefAdapter();
        Object breakfast = chief.makeDinner();
    }
}

interface Chief {
    Object makeBreakfast();

    Object makeDinner();

    Object makeSupper();
}

class Plumber {

    public Object getPipe() {
        return new Object();
    }

    public Object getKey() {
        return new Object();
    }

    public Object getScrewDriver() {
        return new Object();
    }

}


class ChiefAdapter extends Plumber implements Chief {
    @Override
    public Object makeBreakfast() {
        return getPipe();
    }

    @Override
    public Object makeDinner() {
        return getKey();
    }

    @Override
    public Object makeSupper() {
        return getScrewDriver();
    }
}