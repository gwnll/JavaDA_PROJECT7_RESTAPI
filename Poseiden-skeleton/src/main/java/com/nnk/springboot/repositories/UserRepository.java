package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Find user by username property
     * @param username
     */
    @Query(value = "SELECT u FROM User u " +
            "WHERE u.username = :username ")
    Optional<User> findByUsername(String username);
}
