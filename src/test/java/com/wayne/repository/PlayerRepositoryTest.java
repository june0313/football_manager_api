package com.wayne.repository;

import com.wayne.configuration.RootContextConfiguration;
import com.wayne.domain.Player;
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
 * Created by wayne on 2016. 10. 2..
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootContextConfiguration.class)
@ActiveProfiles("test")
@Transactional
public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;

	private Player fixturePlayer;

	@Before
	public void setUp() throws Exception {
		fixturePlayer = new Player();
		fixturePlayer.setEmail("test@test.com");
		fixturePlayer.setNickname("tester");
		fixturePlayer.setPassword("password");
	}

	@Test
	public void testFindNonExistOne() throws Exception {
		// when
		Player player = playerRepository.findOne("test@test.com");

		// then
		assertThat(player).isNull();
	}

	@Test
	public void testFindExistOne() throws Exception {
		// given
		playerRepository.save(fixturePlayer);

		// when
		Player resultPlayer = playerRepository.findOne("test@test.com");

		// then
		assertThat(resultPlayer).isNotNull();
		assertThat(resultPlayer.getEmail()).isEqualTo("test@test.com");
		assertThat(resultPlayer.getNickname()).isEqualTo("tester");
	}
}