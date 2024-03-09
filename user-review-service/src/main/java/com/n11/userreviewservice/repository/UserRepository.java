package com.n11.userreviewservice.repository;


import com.n11.userreviewservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
