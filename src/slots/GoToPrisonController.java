package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class GoToPrisonController extends FieldController{
	desktop_fields.Street goToPrison; 
	GoToPrisonData goToPrisonData;
	GoToPrisonController(GoToPrisonData data)
	{
		goToPrisonData = data;
	}

	@Override
	public void landOnField(Player player) {
		/**
		 * When player lands on GoToPrison he is immediately sent to prison
		 */
		goToPrison.displayOnCenter();
		GUI.showMessage(Translator.getString("LANDONGOTOPRISON", goToPrisonData.getPrisonPosition()));
		player.setPosition(goToPrisonData.getPrisonPosition());
		
	}

	@Override
	public desktop_fields.Field pushToGUI(int position){
		this.position = position;
		goToPrison = new desktop_fields.Street.Builder().setBgColor(new Color(223f/255, 255f/255, 43f/255)).build();
		goToPrison.setDescription(this.getDescription());
		goToPrison.setTitle(this.getName());
		goToPrison.setSubText("You are going to prison!");
		return goToPrison;
	}

}
