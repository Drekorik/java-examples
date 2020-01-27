package org.fireplume.patterns.structural.Flyweight;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by cloudjumper on 8/15/16.
 */
public class FlyweightExample {
    public static void main(String... args) {
        FlyweightFactory factory = new FlyweightFactory();

        int[] characterCodes = {1, 2, 3};
        for (int nextCode : characterCodes) {
            EnglishCharacter character = factory.getCharacter(nextCode);
            character.printCharacter();
        }
    }
}

abstract class EnglishCharacter {
    protected char symbol;
    protected int width;
    protected int height;

    public abstract void printCharacter();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishCharacter that = (EnglishCharacter) o;
        return symbol == that.symbol &&
                width == that.width &&
                height == that.height;
    }

    @Override
    public int hashCode() {

        return Objects.hash(symbol, width, height);
    }
}

class CharacterA extends EnglishCharacter {
    public CharacterA() {
        symbol = 'A';
        width = 10;
        height = 20;
    }

    @Override
    public void printCharacter() {
        System.out.println("Symbol = " + symbol + " Width = " + width + " Height = " + height);
    }
}

class CharacterB extends EnglishCharacter {
    public CharacterB() {
        symbol = 'B';
        width = 20;
        height = 30;
    }

    @Override
    public void printCharacter() {
        System.out.println("Symbol = " + symbol + " Width = " + width + " Height = " + height);
    }
}

class CharacterC extends EnglishCharacter {
    public CharacterC() {
        symbol = 'C';
        width = 40;
        height = 50;
    }

    @Override
    public void printCharacter() {
        System.out.println("Symbol = " + symbol + " Width = " + width + " Height = " + height);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class FlyweightFactory {
    private HashMap<Integer, EnglishCharacter> characters = new HashMap<Integer, EnglishCharacter>();

    public EnglishCharacter getCharacter(int characterCode) {
        EnglishCharacter character = characters.get(new Integer(characterCode));
        if (character == null) {
            switch (characterCode) {
                case 1: {
                    character = new CharacterA();
                    break;
                }
                case 2: {
                    character = new CharacterB();
                    break;
                }
                case 3: {
                    character = new CharacterC();
                    break;
                }
            }
            characters.put(characterCode, character);
        }
        return character;
    }

}

