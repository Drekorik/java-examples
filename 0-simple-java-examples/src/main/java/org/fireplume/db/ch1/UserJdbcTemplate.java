package org.fireplume.db.ch1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(List<UserEntity> entities) {
        List<Object[]> toInsert = entities.stream().map(userEntity -> {
            Object[] objects = new Object[3];
            objects[0] = userEntity.getId();
            objects[1] = userEntity.getName();
            objects[2] = userEntity.getEmail();
            return objects;
        }).collect(Collectors.toList());
        jdbcTemplate.batchUpdate("INSERT INTO public.user (id, name, email) VALUES (?,?,?)", toInsert);
    }

    public UserEntity getOneByName(String name) {
        return jdbcTemplate.query("SELECT id, name, email FROM public.user WHERE name = ?", new Object[]{name},
                rs -> rs.next()
                        ? new UserEntity(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"))
                        : null
        );
    }
}
