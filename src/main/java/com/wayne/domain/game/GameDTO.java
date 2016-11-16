package com.wayne.domain.game;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wayne on 2016. 11. 15..
 *
 */
@Getter
@Setter
public class GameDTO {
	private Long playerId;
	private Long clubId;
	private String title;
}
