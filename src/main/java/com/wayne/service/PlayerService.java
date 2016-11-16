package com.wayne.service;

import com.wayne.domain.club.Club;
import com.wayne.domain.player.Player;
import com.wayne.domain.player.PlayerDTO;
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

	public Player find(Long id) {
		return playerRepository.findOne(id);
	}

	public List<Player> findAll() {
		return playerRepository.findAll();
	}

	public Club findClubByPlayerId(Long id) {
		Player player = this.find(id);

		if (null == player) {
			throw new IllegalStateException("존재하지 않는 선수입니다.");
		}

		return player.getClub();
	}

	public Player editPlayer(Long id, PlayerDTO dto) {
		Player player = this.find(id);

		if (null == player) {
			throw new IllegalStateException("존재하지 않는 선수입니다.");
		}

		return player.update(dto);
	}

}
