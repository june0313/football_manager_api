package com.wayne.repository;

import com.wayne.configuration.RootContextConfiguration;
import com.wayne.domain.Club;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Created by wayne on 2016. 10. 10..
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootContextConfiguration.class)
@ActiveProfiles("test")
@Transactional
@Slf4j
public class ClubRepositoryTest {

	@Autowired
	private ClubRepository clubRepository;

	@Test
	public void testSave() throws Exception {
		Club club = new Club();
		club.setName("club1");

		clubRepository.save(club);
	}

	@Test
	public void testFindOne() throws Exception {
		// given
		Club club = new Club();
		club.setName("club1");
		clubRepository.save(club);
		log.debug("--------------club id : " + club.getId());

		// when
		Club findClub = clubRepository.findOne(club.getId());

		// then
		assertThat(findClub.getName()).isEqualTo("club1");
		assertThat(findClub.getPlayers().size()).isZero();
	}

}