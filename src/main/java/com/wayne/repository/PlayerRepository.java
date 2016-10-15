package com.wayne.repository;

import com.wayne.domain.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

	public Player findOne(Long id) {
		return entityManager.find(Player.class, id);
	}

	public List<Player> findByEmail(String email) {
		String queryString = "select p from Player p where p.email=:email";

		return entityManager.createQuery(queryString, Player.class)
			.setParameter("email", email)
			.getResultList();
	}

}
