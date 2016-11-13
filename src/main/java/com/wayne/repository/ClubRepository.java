package com.wayne.repository;

import com.wayne.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
