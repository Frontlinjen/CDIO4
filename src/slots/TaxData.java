package slots;

public class TaxData extends FieldData{

	private int taxAmount;
	private int taxRate;


	public int getTaxAmount() {
		return taxAmount;
	}


	public int getTaxRate() {
		return taxRate;
	}


	public TaxData(int i, int price, int taxPercentage) {
		super(i);
		this.taxAmount = price;
		taxRate = taxPercentage;
	}

}
