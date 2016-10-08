package com.wayne.repository;

import com.wayne.domain.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public class PlayerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Player player) {
		entityManager.persist(player);
	}

	public Player findOne(String email) {
		return entityManager.find(Player.class, email);
	}

}
