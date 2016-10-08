package com.wayne.controller.player;

import com.wayne.domain.Player;
import com.wayne.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
