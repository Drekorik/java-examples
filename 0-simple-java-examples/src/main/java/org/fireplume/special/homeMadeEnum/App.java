package org.fireplume.special.homeMadeEnum;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        ExampleEnum exEnum11 = ExampleEnum.ENUM1;
        System.out.println(exEnum11);
        ExampleEnum exEnum12 = ExampleEnum.valueOf("ENUM1");
        System.out.println(exEnum12);
        System.out.println(Arrays.toString(ExampleEnum.values()));
        System.out.println(exEnum11.ordinal());

        System.out.println("-----------------------------");

        CustomEnum myEnum11 = CustomEnum.ENUM1;
        System.out.println(myEnum11);
        CustomEnum myEnum12 = CustomEnum.valueOf("ENUM1");
        System.out.println(myEnum12);
        System.out.println(Arrays.toString(CustomEnum.values()));
        System.out.println(myEnum11.ordinal());

    }
}
