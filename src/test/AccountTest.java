package test;
import game.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	@Test
	public void testGetGold() {
		Account account = new Account(5000,"Sheep");
		assertTrue(account.getGold()==5000);
	}

	@Test
	public void testWithdraw() {
		Account account = new Account(5000,"Sheep");
		assertTrue("Fail, you should be able to withdraw gold",account.withdraw(500));
		assertTrue("Fail, there should be 4500left", account.getGold()==4500);
		assertFalse("Fail, you should not be able to withdraw more than your total gold",account.withdraw(Integer.MAX_VALUE));
	}

	@Test
	public void testTransferTo() {
		Account account1 = new Account(5000,"Sheep");
		Account account2 = new Account(5000, "Isbjoern");
		account1.transferTo(account2, 5000);
		assertTrue("Fail, it should have transfered 5000gold",account1.getGold()==0 && account2.getGold()==10000);
		account1.addGold(5000);
		account1.transferTo(account2, 10000);
		assertTrue("Fail, you should only be able to receive the amount of gold that the opponent has",account1.getGold()==0 && account2.getGold()==15000);
	}

	@Test
	public void testAddGold() {
		Account account = new Account(5000,"Sheep");
		account.addGold(5000);
		assertTrue("Fail, there should be 10000gold in the account",account.getGold()==10000);
	}

	@Test
	public void testRemoveGold() {
		Account account = new Account(5000,"Sheep");
		account.removeGold(5000);
		assertTrue("Fail, there should not be any gold in the account",account.getGold()==0);
		account.removeGold(1);
		assertFalse("Fail, you can have negativ gold",account.getGold()==-1);
	}

}