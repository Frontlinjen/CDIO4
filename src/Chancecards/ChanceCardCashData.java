package Chancecards;

public class ChanceCardCashData extends ChanceCardData{
	private int money;
	
	public ChanceCardCashData(int translateID, int tax) {
		super(translateID);
		this.money = tax;
	}
	
	public int getMoney() {
		return money;
	}
	
	public String toString(){
		return "getMoney()=" + getMoney();
	}
}
