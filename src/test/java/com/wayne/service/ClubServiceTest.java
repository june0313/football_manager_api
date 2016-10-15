package com.wayne.service;

import com.wayne.configuration.RootContextConfiguration;
import com.wayne.domain.Club;
import com.wayne.domain.Player;
import com.wayne.repository.ClubRepository;
import com.wayne.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Created by wayne on 2016. 10. 15..
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootContextConfiguration.class)
@ActiveProfiles("test")
@Transactional
public class ClubServiceTest {

	@Autowired
	private ClubService clubService;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Before
	public void setUp() throws Exception {
		Player fixturePlayer = new Player();
		fixturePlayer.setNickname("fixture");
		fixturePlayer.setPassword("password");
		fixturePlayer.setEmail("test@test.com");

		playerRepository.save(fixturePlayer);
	}

	@Test(expected = IllegalStateException.class)
	public void testCreateClubWhenPlayerIsNull() throws Exception {
		// given : 존재하지 않는 선수의 id를 설정한다.
		Long notExistPlayerId = 100L;

		// when : 존재하지 않는 선수로 클럽 생성을 시도한다.
		clubService.createClub(notExistPlayerId, "testClub");

		// then : IllegalStateException 이 발생한다.
	}

	@Test(expected = IllegalStateException.class)
	public void testCreateClubWhenPlayerHasClub() throws Exception {
		// given : 이미 클럽에 가입된 선수를 준비한다.
		Player existPlayer = playerRepository.findByEmail("test@test.com").get(0);
		existPlayer.setClub(new Club());

		// when : 이미 클럽에 소속된 선수로 클럽 생성을 시도한다.
		clubService.createClub(existPlayer.getId(), "newClub");

		// then : IllegalStateException 이 발생한다.
	}

	@Test
	public void testCreateClub() throws Exception {
		// given : 클럽에 소속되어있지 않은 선수를 준비한다.
		Player existPlayer = playerRepository.findByEmail("test@test.com").get(0);

		// when : 클럽 생성을 시도한다.
		Club club = clubService.createClub(existPlayer.getId(), "newClub");

		// then : 클럽이 정상적으로 생성되고 선수와의 연관관계가 정상적으로 매핑 되어야 한다.
		assertThat(club).isNotNull();
		assertThat(club.getName()).isEqualTo("newClub");
		assertThat(club.getCreator()).isEqualTo(existPlayer);
		assertThat(club.getPlayers().get(0)).isEqualTo(existPlayer);
		assertThat(existPlayer.getClub()).isNotNull();
		assertThat(existPlayer.getClub()).isEqualTo(club);
	}

	@Test
	public void clubShouldBeFound() throws Exception {
		// given : 클럽을 저장한다.
		Club club = new Club();
		club.setName("clubName");
		clubRepository.save(club);

		// when : 저장된 클럽을 찾는다.
		Club foundClub = clubService.find(club.getId());

		// then : 찾은 클럽 정보가 저장했던 클럽 정보와 일치해야한다.
		assertThat(foundClub.getName()).isEqualTo("clubName");
	}
}