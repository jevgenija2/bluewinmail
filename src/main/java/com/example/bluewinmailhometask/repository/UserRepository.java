package com.example.bluewinmailhometask.repository;

import com.example.bluewinmailhometask.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailAddress(String emailAddress);
}
