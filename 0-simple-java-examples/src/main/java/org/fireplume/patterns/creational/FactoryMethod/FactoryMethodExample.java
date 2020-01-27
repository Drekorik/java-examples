package org.fireplume.patterns.creational.FactoryMethod;

/**
 * Created by cloudjumper on 8/14/16.
 */
// Шаблон проектирования, предоставляющий подклассам интерфейс для создания экземпляров некоторого класса.
// В момент создания наследники могут определить, какой класс создавать.
// Иными словами, Фабрика делегирует создание объектов наследникам родительского класса.
// Это позволяет использовать в коде программы не специфические классы,
// а манипулировать абстрактными объектами на более высоком уровне.
public class FactoryMethodExample {
    public static void main(String... args) {
        AbstractCreator[] creators = {new Creator1(), new Creator2()};
        for (AbstractCreator creator : creators) {
            AbstractProduct product = creator.factoryMethod();
            System.out.println(product.getClass());
        }
    }
}

class AbstractProduct {
}

class Product1 extends AbstractProduct {
}

class Product2 extends AbstractProduct {
}

abstract class AbstractCreator {
    public abstract AbstractProduct factoryMethod();
}

class Creator1 extends AbstractCreator {
    @Override
    public AbstractProduct factoryMethod() {
        return new Product1();
    }
}

class Creator2 extends AbstractCreator {
    @Override
    public AbstractProduct factoryMethod() {
        return new Product2();
    }
}
