package com.wayne.domain.game;

import com.wayne.domain.club.Club;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wayne on 2016. 11. 13..
 *
 */
@Entity
@Table(name = "GAME")
@Getter
@Setter
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAMD_ID", nullable = false)
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HOME_CLUB")
	private Club homeClub;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AWAY_CLUB")
	private Club awayClub;

	@Enumerated(value = EnumType.STRING)
	private GameState gameState;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@PrePersist
	private void prePersist() {
		gameState = GameState.WAIT_FOR_APPLY;
		createdDate = new Date();
		updatedDate = new Date();
	}

	@PreUpdate
	private void preUpdate() {
		updatedDate = new Date();
	}

	public static Game create(Club homeClub, String title) {
		Game game = new Game();
		game.setHomeClub(homeClub);
		game.setTitle(title);
		return game;
	}
}
