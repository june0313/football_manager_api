package com.wayne.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author wayne
 * @version 1.0
 */
@Entity
@Table(name = "CLUB")
@Getter
@Setter
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToOne
	@JoinColumn(name = "CREATOR")
	@JsonIgnore
	private Player creator;

	@OneToMany(mappedBy = "club")
	@JsonIgnore
	private List<Player> players = Lists.newArrayList();

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@PrePersist
	private void prePersist() {
		createdDate = new Date();
		updatedDate = new Date();
	}

	@PreUpdate
	private void preUpdate() {
		updatedDate = new Date();
	}

	public static Club create(Player creator, String clubName) {
		Club club = new Club();
		club.setName(clubName);
		club.setCreator(creator);
		return club;
	}

	public void addPlayer(Player player) {
		// TODO : 중북되는 선수에대한 처리 필요
		this.players.add(player);
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
	}

}
