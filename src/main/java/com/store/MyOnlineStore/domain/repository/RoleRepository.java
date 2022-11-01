package com.store.MyOnlineStore.domain.repository;

import com.store.MyOnlineStore.domain.entities.CommerceRole;
import com.store.MyOnlineStore.domain.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, CommerceRole> {
    Optional<Role> findByCommerceRole(CommerceRole commerceRole);
}
