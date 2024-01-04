package com.microservice.passwordmanagerservice.repo;

import com.microservice.passwordmanagerservice.entity.PasswordEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepo extends JpaRepository<PasswordEntity,Long> {
    PasswordEntity findByusername(String username);
    @Transactional
    void deleteByusername(String username);
}
