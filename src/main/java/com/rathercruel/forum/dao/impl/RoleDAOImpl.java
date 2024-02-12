package com.rathercruel.forum.dao.impl;

import com.rathercruel.forum.dao.RoleDAO;
import com.rathercruel.forum.models.Role;
import com.rathercruel.forum.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private RoleRepository repository;

    @Override
    public Optional<Role> getByAuthority(String authority) {
        return repository.findByAuthority(authority);
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public void delete(Role role) {
        repository.delete(role);
    }
}
