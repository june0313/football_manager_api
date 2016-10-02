import com.wayne.domain.Player;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.fail;

/**
 * @author wayne
 * @version 1.0
 * 순수 Java 기반에서의 JPA 테스트
 */
public class JpaTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player();
		player.setEmail("test@test.com");
		player.setNickname("tester");
		player.setPassword("password");
	}

	@Test
	public void testJpa() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(player);

			player.setNickname("fixedName");

			Player player1 = em.find(Player.class, "test@test.com");
			System.out.println(player1);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			fail();
		} finally {
			em.close();
		}

		emf.close();
	}
}
