package com.wayne.service;

import com.wayne.domain.Player;
import com.wayne.exception.DuplicatePlayerException;
import com.wayne.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
@Service
@Transactional
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public void signUp(Player player) throws Exception {
		// 중복 회원 검증
		validateDuplicatePlayer(player);
		playerRepository.save(player);
	}

	private void validateDuplicatePlayer(Player player) {
		List<Player> foundPlayerList = playerRepository.findByEmail(player.getEmail());
		if (foundPlayerList.size() > 0) {
			throw new DuplicatePlayerException(foundPlayerList.get(0).getEmail());
		}
	}
}
