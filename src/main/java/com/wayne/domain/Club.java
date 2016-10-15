package com.wayne.domain;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(mappedBy = "club")
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

}
