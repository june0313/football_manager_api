package com.wayne.repository;

import com.wayne.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wayne on 2016. 11. 15..
 *
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
