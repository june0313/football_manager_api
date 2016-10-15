package com.wayne.repository;

import com.wayne.domain.Club;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public class ClubRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Club club) {
		entityManager.persist(club);
	}

	public Club findOne(Long clubId) {
		return entityManager.find(Club.class, clubId);
	}

}
