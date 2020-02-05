package org.fireplume.services.security;

import org.springframework.stereotype.Component;

@Component("someSecurityService")
public class SomeSecurityService {
    public boolean isString(Object s) {
        System.out.println("IS_STRING_SEC_METH");
        return s instanceof String;
    }

    public boolean isNotNull(String s) {
        System.out.println("IS_NOT_NULL_SEC_METH");
        return s != null;
    }
}
