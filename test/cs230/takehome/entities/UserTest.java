package cs230.takehome.entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UserTest {
	private User user1;

	@Before
	public void setUp() throws Exception {
		user1 = new User("nadmin", "admin", "Noreen");
	}

	@After
	public void tearDown() throws Exception {
		// nothing to do, since we don't use the DB for this test!
		// if we did, we'd need to do some clean-up here...
	}

	@Test
	public void testGetUsername() {
		Assert.assertEquals("nadmin", user1.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		Assert.assertEquals("admin", user1.getPassword());
	}

}
