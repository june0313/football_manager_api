package com.wayne.domain;

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
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NICKNAME")
	private String nickname;

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

}
