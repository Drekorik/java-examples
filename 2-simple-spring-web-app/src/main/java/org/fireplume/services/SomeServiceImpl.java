package org.fireplume.services;

import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public String getStringWithoutSpaces(String someString) {
        return someString.replaceAll(" ", "");
    }
}
