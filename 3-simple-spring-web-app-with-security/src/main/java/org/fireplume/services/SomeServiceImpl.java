package org.fireplume.services;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SomeServiceImpl implements SomeService {
    @Override
    public String getStringWithoutSpaces(String someString) {
        return someString.replaceAll(" ", "");
    }

    @Override
    @PostAuthorize("hasPermission(returnObject, 'ROLE_USER') and @someSecurityService.isString(returnObject)")
    @PreAuthorize("@someSecurityService.isNotNull(#someString)")
    public String securedWithPermissionEvaluator(String someString) {
        return "*** Secured method ***";
    }
}
