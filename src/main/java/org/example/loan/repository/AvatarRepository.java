package org.example.loan.repository;

import org.example.loan.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, String> {
    Optional<Avatar> findByName(String name);
}
