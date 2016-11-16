package com.wayne.controller.club;

import com.wayne.domain.club.Club;
import com.wayne.domain.club.ClubDTO;
import com.wayne.domain.player.Player;
import com.wayne.service.ClubService;
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
public class ClubController {

	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "/clubs", method = RequestMethod.POST)
	public ResponseEntity<Long> createClub(@RequestBody ClubDTO dto) {
		Club club = clubService.createClub(dto.getPlayerId(), dto.getClubName());
		return new ResponseEntity<>(club.getId(), HttpStatus.OK);
	}

	@RequestMapping(value = "/clubs", method = RequestMethod.GET)
	public ResponseEntity<List<Club>> getClubs() {
		List<Club> clubs = clubService.findAll();
		return new ResponseEntity<>(clubs, HttpStatus.OK);
	}

	@RequestMapping(value = "/clubs/{id}", method = RequestMethod.GET)
	public ResponseEntity<Club> getClub(@PathVariable Long id) {
		Club club = clubService.find(id);
		return new ResponseEntity<>(club, HttpStatus.OK);
	}

	@RequestMapping(value = "/clubs/{id}/players", method = RequestMethod.GET)
	public ResponseEntity<List<Player>> getClubPlayers(@PathVariable Long id) {
		List<Player> players = clubService.findClubPlayers(id);
		return new ResponseEntity<>(players, HttpStatus.OK);
	}

	@RequestMapping(value = "/clubs/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyClubInfo(@PathVariable Long id, @RequestBody ClubDTO dto) {
		clubService.update(id, dto);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

}
