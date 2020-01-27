package org.fireplume.patterns.structural.Bridge;


/**
 * Created by cloudjumper on 8/15/16.
 */
public class BridgeExample {
    public static void main(String... args) {
        Shape[] shapes = {
                new Circle(5, 10, 10, new LargeCircleDrawer()),
                new Circle(20, 30, 100, new SmallCircleDrawer())};

        for (Shape next : shapes) {
            next.draw();
        }
    }
}

interface Drawer {
    void drawCircle(int x, int y, int radius);
}

class SmallCircleDrawer implements Drawer {
    private static final double radiusMultiplier = 0.25;

    @Override
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Small circle center = " + x + "," + y + " radius = " + radius * radiusMultiplier);
    }
}

class LargeCircleDrawer implements Drawer {
    private static final double radiusMultiplier = 10;

    @Override
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Large circle center = " + x + "," + y + " radius = " + radius * radiusMultiplier);
    }
}

abstract class Shape {
    protected Drawer drawer;

    protected Shape(Drawer drawer) {
        this.drawer = drawer;
    }

    public abstract void draw();

    public abstract void enlargeRadius(int multiplier);
}

class Circle extends Shape {
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y, int radius, Drawer drawer) {
        super(drawer);
        setX(x);
        setY(y);
        setRadius(radius);
    }

    @Override
    public void draw() {
        super.drawer.drawCircle(this.x, this.y, this.radius);
    }

    @Override
    public void enlargeRadius(int multiplier) {
        this.radius *= multiplier;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}