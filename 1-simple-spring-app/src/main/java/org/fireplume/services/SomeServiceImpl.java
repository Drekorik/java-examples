package org.fireplume.services;

public class SomeServiceImpl implements SomeService {
    @Override
    public String printSomeStringWithoutSpaces(String someString) {
        return someString.replaceAll(" ", "");
    }
}
