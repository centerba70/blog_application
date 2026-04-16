package com.interviews.__demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<BlogUser, Long> {

    List<BlogUser> findByUsername(String username);

}