package org.fireplume.patterns.structural.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cloudjumper on 8/15/16.
 */
public class CompositeExample {
    public static void main(String... args) {
        Ellipse ellipse1 = new Ellipse();
        Ellipse ellipse2 = new Ellipse();
        Ellipse ellipse3 = new Ellipse();
        Ellipse ellipse4 = new Ellipse();

        //Initialize three composite graphics
        CompositeGraphic graphic = new CompositeGraphic();
        CompositeGraphic graphic1 = new CompositeGraphic();
        CompositeGraphic graphic2 = new CompositeGraphic();

        //Composes the graphics
        graphic1.add(ellipse1);
        graphic1.add(ellipse2);
        graphic1.add(ellipse3);

        graphic2.add(ellipse4);

        graphic.add(graphic1);
        graphic.add(graphic2);

        //Prints the complete graphic (Four times the string "Ellipse").
        graphic.print();
    }
}

// graphic -> {
//      graphic1 -> {
//          ellipse1, ellipse2, ellipse3
//      },
//      graphic2 -> {
//          ellipse4
//      }
// }


interface Graphic {
    public void print();
}

class CompositeGraphic implements Graphic {
    private List<Graphic> mChildGraphics = new ArrayList<Graphic>();

    @Override
    public void print() {
        for (Graphic graphic : mChildGraphics) {
            graphic.print();
        }
    }

    public void add(Graphic graphic) {
        mChildGraphics.add(graphic);
    }

    public void remove(Graphic graphic) {
        mChildGraphics.remove(graphic);
    }
}

class Ellipse implements Graphic {
    @Override
    public void print() {
        System.out.println("Ellipse");
    }
}