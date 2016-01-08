package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class TaxController extends FieldController {
	desktop_fields.Tax tax;
	TaxData taxData;
	TaxController(TaxData data)
	{
		taxData = data;
	}
	@Override
	public void landOnField(Player player) {
		/**
		 * Player lands on Tax and has to pay, either a flat amount or
		 * a percentage of his fortune. 
		 */
		if (taxRate == 0){
			GUI.getUserButtonPressed(Translator.getString("LANDONTAX"), Integer.toString(taxAmount));
		}
		else {
			tax.displayOnCenter();
			if (GUI.getUserLeftButtonPressed(Translator.getString("LANDONTAX"), Integer.toString(taxRate) , Integer.toString(taxAmount))) {
				player.getAccount().withdraw(taxRate);
			}
			else {
				player.getAccount().withdraw(taxAmount);
			}
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

}
