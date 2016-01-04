package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class Tax extends Field{

	private int taxAmount;
	private int taxRate = -1;
	desktop_fields.Tax tax;
	
	public Tax(int i, Types type, int price) {
		super(i, type);
		this.taxAmount = price;
	}

	@Override
	public void landOnField(Player player) {
		/**
		 * Player lands on Tax and has to pay, either a flat amount or
		 * a percentage of his fortune. 
		 */
		tax.displayOnCenter();
		if (GUI.getUserLeftButtonPressed(Translator.getString("LANDONTAX"), Translator.getString("TAX%"), Translator.getString("TAXCASH",taxAmount))) {
			player.getAccount().withdraw((player.getAccount().getGold()/10));
		}
		else {
			player.getAccount().withdraw(taxAmount);
		}
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		this.position = position;
		tax = new desktop_fields.Tax.Builder().setBgColor(new Color(255f/255, 43f/255, 57f/255)).build();
		tax.setDescription(this.getDescription());
		tax.setTitle(this.getName());
		tax.setSubText(taxAmount + "");
		return tax;
	}
	
	@Override
	public String toString() {
		return "Tax [taxAmount=" + taxAmount + ", tax=" + tax + ", position=" + position
				+ ", getName()=" + getName() + ", getPosition()= " + getPosition() + "]";
	}
	
}
