package com.wayne.controller.game;

import com.wayne.domain.game.Game;
import com.wayne.domain.game.GameDTO;
import com.wayne.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wayne on 2016. 11. 15..
 *
 */
@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> getGames() {
		List<Game> gameList = gameService.findAll();
		return new ResponseEntity<>(gameList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/games/{id}")
	public ResponseEntity<Game> getGame(@PathVariable Long id) {
		Game game = gameService.findOne(id);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}

	@RequestMapping(value = "/games", method = RequestMethod.POST)
	public ResponseEntity<Game> createGame(@RequestBody GameDTO dto) {
		Game game = gameService.createGame(dto);
		return new ResponseEntity<>(game, HttpStatus.OK);
	}

}
