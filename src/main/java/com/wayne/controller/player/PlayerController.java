package com.wayne.controller.player;

import com.wayne.domain.Club;
import com.wayne.domain.Player;
import com.wayne.domain.PlayerDTO;
import com.wayne.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
@RestController
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	/**
	 * 회원 가입
	 * @return ResponseEntity with OK
	 */
	@RequestMapping(value = "/players", method = RequestMethod.POST)
	public ResponseEntity<String> signUp(@RequestBody Player player) throws Exception {
		playerService.signUp(player);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	/**
	 * Player 상세 정보 조회
	 * @param id player id
	 * @return player
	 */
	@RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
	public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
		Player player = playerService.find(id);
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

	/**
	 * Player 목록 조회
	 * @return Player 정보 목록
	 */
	@RequestMapping(value = "/players", method = RequestMethod.GET)
	public ResponseEntity<List<Player>> getPlayers() {
		List<Player> players = playerService.findAll();
		return new ResponseEntity<>(players, HttpStatus.OK);
	}

	/**
	 * Player가 속한 Club정보 조회
	 * @param id player id
	 * @return player가 속한 club
	 */
	@RequestMapping(value = "/players/{id}/clubs", method = RequestMethod.GET)
	public ResponseEntity<Club> getPlayerClub(@PathVariable Long id) {
		Club club = playerService.findClubByPlayerId(id);
		return new ResponseEntity<>(club, HttpStatus.OK);
	}

	@RequestMapping(value = "/players/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Player> editPlayer(@PathVariable Long id, @RequestBody PlayerDTO dto) {
		Player player = playerService.editPlayer(id, dto);
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

}
