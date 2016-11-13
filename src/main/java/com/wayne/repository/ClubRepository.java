package com.wayne.repository;

import com.wayne.domain.Club;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

	public List<Club> findAll() {
		String queryString = "SELECT c FROM Club c";
		return entityManager.createQuery(queryString, Club.class).getResultList();
	}

}
