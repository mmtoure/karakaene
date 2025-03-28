package com.mmt.karakaene.repository;

import com.mmt.karakaene.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ValidationRepository extends JpaRepository<Validation, Long> {
}
