package com.mmt.karakaene.repository;

import com.mmt.karakaene.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
