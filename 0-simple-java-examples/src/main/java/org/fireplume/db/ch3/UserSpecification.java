package org.fireplume.db.ch3;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserSpecification {
    public static Specification<UserEntity> userByName(final String name) {
        return (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.get("name"), name);
    }
}
