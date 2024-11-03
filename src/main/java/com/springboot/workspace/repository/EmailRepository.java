package com.springboot.workspace.repository;

import com.springboot.workspace.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    Optional<Email> findByName(String email);
    List<Email> findByNameIn(List<String> names);
    Optional<Email> findByContent(String content);
}
