package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUtilities {

	@Test
	public void createProxyWithAccount() {
		assertTrue(mware_lib.Utilities.createProxy("name",
				"cash_access.Account", "localhost", 12345) instanceof cash_access.Account);
	}

	@Test(expected = RuntimeException.class)
	public void createProxyWithManager() {
		mware_lib.Utilities.createProxy("name", "branch_access.Manager",
				"localhost", 12345);
	}

	@Test
	public void createProxyWithSomethingElse() {
		assertNull(mware_lib.Utilities.createProxy("name", "java.lang.String",
				"localhost", 12345));
	}

	@Test
	public void testGetTypeForObject() {
		assertEquals("cash_access.Account",
				Utilities.getTypeForObject(new cash_access.AccountDummy()));
	}

}
