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
		if (taxData.getTaxRate() == 0){
			GUI.getUserButtonPressed(Translator.getString("LANDONTAX"), Integer.toString(taxData.getTaxAmount()));
		}
		else {
			tax.displayOnCenter();
			if (GUI.getUserLeftButtonPressed(Translator.getString("LANDONTAX"), Integer.toString(taxData.getTaxRate()) , Integer.toString(taxData.getTaxAmount()))) {
				player.getAccount().withdraw(taxData.getTaxRate());
			}
			else {
				player.getAccount().withdraw(taxData.getTaxAmount());
			}
		}
	}

	@Override
	public desktop_fields.Field pushToGUI(int position) {
		taxData.setPosition(position);
		tax = new desktop_fields.Tax.Builder().setBgColor(new Color(255f/255, 43f/255, 57f/255)).build();
		tax.setDescription(taxData.getDescription());
		tax.setTitle(taxData.getName());
		tax.setSubText(Integer.toString(taxData.getTaxAmount()));
		return tax;
	}

}
