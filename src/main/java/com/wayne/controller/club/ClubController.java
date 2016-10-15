package com.wayne.controller.club;

import com.wayne.domain.Club;
import com.wayne.domain.ClubCreationDTO;
import com.wayne.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wayne
 * @version 1.0
 */
@RestController
public class ClubController {

	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "/clubs", method = RequestMethod.POST)
	public ResponseEntity<Long> createClub(@RequestBody ClubCreationDTO dto) {
		Club club = clubService.createClub(dto.getPlayerId(), dto.getClubName());
		return new ResponseEntity<>(club.getId(), HttpStatus.OK);
	}
}
