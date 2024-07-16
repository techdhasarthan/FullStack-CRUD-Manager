package com.backend.restApi.demo.repository;

import com.backend.restApi.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
