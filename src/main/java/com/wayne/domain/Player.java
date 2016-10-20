package com.wayne.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author wayne
 * @version 1.0
 */
@Entity
@Table(name = "PLAYER")
@Getter
@Setter
@ToString
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "PASSWORD")
	@JsonIgnore
	private String password;

	@Column(name = "NICKNAME")
	private String nickname;

	@ManyToOne
	@JoinColumn(name = "CLUB_ID")
	@JsonIgnore
	private Club club;

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

	public void setClub(Club club) {
		// 기존 클럽과의 관계 제거
		if (null != this.club) {
			this.club.removePlayer(this);
		}

		this.club = club;

		if (null != club) {
			club.addPlayer(this);
		}
	}

	public Player update(PlayerDTO dto) {
		if (!Strings.isNullOrEmpty(dto.getNickName())) {
			this.nickname = dto.getNickName();
		}

		if (!Strings.isNullOrEmpty(dto.getPassword())) {
			this.password = dto.getPassword();
		}

		return this;
	}

}
