package Chancecards;

public class ChanceCardCashData extends ChanceCardData{
	private int money;
	
	public ChanceCardCashData(int translateID, int money) {
		super(translateID);
		this.money = money;
	}
	
	public int getMoney() {
		return money;
	}
	
	public String toString(){
		return "getMoney()=" + getMoney();
	}
}
