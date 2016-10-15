package com.wayne.service;

import com.wayne.domain.Club;
import com.wayne.domain.Player;
import com.wayne.repository.ClubRepository;
import com.wayne.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wayne
 * @version 1.0
 */
@Service
@Transactional
public class ClubService {

	@Autowired
	private ClubRepository clubRepository;
	@Autowired
	private PlayerRepository playerRepository;

	public Club find(Long clubId) {
		return clubRepository.findOne(clubId);
	}

	public Club createClub(Long playerId, String clubName) {
		// 선수 엔티티 조회
		Player player = playerRepository.findOne(playerId);

		if (null == player) {
			throw new IllegalStateException("존재하지 않는 선수입니다");
		}

		if (null != player.getClub()) {
			throw new IllegalStateException("이미 가입한 클럽이 존재합니다.");
		}

		// 클럽 생성
		Club club = Club.create(player, clubName);

		// 클럽 저장
		clubRepository.save(club);

		// 연관관계 설정
		player.setClub(club);

		return club;
	}
}
