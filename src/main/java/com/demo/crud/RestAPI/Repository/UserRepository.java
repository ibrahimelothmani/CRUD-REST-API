package com.demo.crud.RestAPI.Repository;

import com.demo.crud.RestAPI.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<UserInfo> findAllByOrderByCreatedAtDesc();
    Optional<UserInfo> findUserById(Long id);
}
