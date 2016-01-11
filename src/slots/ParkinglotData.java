package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Account;
import game.Player;
import game.Translator;

public class ParkinglotData extends FieldData{


	private Account balance;
	public void TransferBonus(Account acc)
	{
		balance.transferTo(acc, balance.getGold());
	}

	public ParkinglotData(int i, Account acc) {
		super(i);
		this.balance = acc;
	}
	
	public Account getAccount(){
		return balance;
	}



}
