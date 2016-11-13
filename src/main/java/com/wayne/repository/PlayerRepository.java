package com.wayne.repository;

import com.wayne.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
	List<Player> findByEmail(String email);
}
