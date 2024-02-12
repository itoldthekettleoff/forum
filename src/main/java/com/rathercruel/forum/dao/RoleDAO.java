package com.rathercruel.forum.dao;

import com.rathercruel.forum.models.Role;

import java.util.Optional;

public interface RoleDAO {
    Optional<Role> getByAuthority(String authority);
    Role save(Role role);
    void delete(Role role);
}
