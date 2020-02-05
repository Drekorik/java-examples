package org.fireplume.services;

public interface SomeService {
    String getStringWithoutSpaces(String someString);

    String securedWithPermissionEvaluator(String someString);
}
