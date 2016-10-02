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
	private EntityManager em;

	public void save(Player player) {
		em.persist(player);
	}

	public Player findOne(String email) {
		return em.find(Player.class, email);
	}

}
