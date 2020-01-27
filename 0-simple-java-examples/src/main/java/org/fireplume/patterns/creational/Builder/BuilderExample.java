package org.fireplume.patterns.creational.Builder;

/**
 * Created by cloudjumper on 8/14/16.
 */
public class BuilderExample {
    public static void main(String... args) {
        Line l1 = new Line.Builder(0, 23).color("RED").thin(0.5f).build();
        l1.getConfigs();
    }
}

class Line {
    private int point1x;
    private int point2x;
    private String color;
    private float thin;

    public static class Builder {
        //Обязательные
        private int point1x;
        private int point2x;
        //не обязательные
        private String color = "GREEN";
        private float thin = 1.0f;

        public Builder(int point1x, int point2x) {
            this.point1x = point1x;
            this.point2x = point2x;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder thin(float thin) {
            this.thin = thin;
            return this;
        }

        public Line build() {
            return new Line(this);
        }
    }

    private Line(Builder builder) {
        this.point1x = builder.point1x;
        this.point2x = builder.point2x;
        this.color = builder.color;
        this.thin = builder.thin;
    }

    public void getConfigs() {
        System.out.println("Point1x -> " + this.point1x + "\n" +
                "Point2x -> " + this.point2x + "\n" +
                "Color   -> " + this.color + "\n" +
                "Thin    -> " + this.thin);
    }
}
