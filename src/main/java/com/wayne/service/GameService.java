package com.wayne.service;

import com.wayne.domain.club.Club;
import com.wayne.domain.game.Game;
import com.wayne.domain.game.GameDTO;
import com.wayne.repository.ClubRepository;
import com.wayne.repository.GameRepository;
import com.wayne.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wayne on 2016. 11. 15..
 *
 */
@Service
@Transactional
public class GameService {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private ClubRepository clubRepository;

	public List<Game> findAll() {
		return gameRepository.findAll();
	}

	public Game findOne(Long gameId) {
		return gameRepository.findOne(gameId);
	}

	public Game createGame(GameDTO dto) {
		Club homeClub = playerService.findClubByPlayerId(dto.getPlayerId());

		Game game = Game.create(homeClub, dto.getTitle());

		return gameRepository.save(game);
	}

}