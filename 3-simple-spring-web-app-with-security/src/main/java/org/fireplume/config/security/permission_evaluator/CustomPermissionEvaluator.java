package org.fireplume.config.security.permission_evaluator;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * This class provides ability to use hasPermission in Pre/PostAuthorized expressions
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    /**
     * @param authentication     authentication info
     * @param targetDomainObject object (returned object)
     * @param permission         permission that authentication must have
     * @return true if granted, false if not
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("HAS_PERMISSION_SEC_METH");
        return (authentication != null) && (targetDomainObject != null) && permission instanceof String &&
                authentication.getAuthorities().stream().anyMatch(o -> o.getAuthority().equals(permission));

    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}