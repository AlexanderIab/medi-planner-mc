package com.iablonski.users.repository;

import com.iablonski.users.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);

    @Query("SELECT u from User u where " +
            "(:email is null or :email='' or lower(u.email) like lower(concat('%',:email,'%'))) " +
            "and (:username is null or :username='' or lower(u.username) like lower(concat('%',:username,'%')))")
    Page<User> findByParams(@Param("email") String email, @Param("username") String username, Pageable pageable);
}