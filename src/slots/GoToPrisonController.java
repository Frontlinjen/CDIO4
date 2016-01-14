package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class GoToPrisonController extends FieldController{
	desktop_fields.Jail goToPrison; 
	GoToPrisonData goToPrisonData;
	public GoToPrisonController(GoToPrisonData data)
	{
		super(data);
		goToPrisonData = data;
	}

	@Override
	public void landOnField(Player player) {
		/**
		 * When player lands on GoToPrison he is immediately sent to prison, and will remain there until he gets a double or 3 turns pass.
		 */
		goToPrison.displayOnCenter();
		GUI.showMessage(Translator.getString("LANDONGOTOPRISON", goToPrisonData.getPrisonPosition()));
		player.setNextPosition(goToPrisonData.getPrisonPosition(),false);
		goToPrisonData.getPrison().addInmate(player);
	}

	@Override
	public desktop_fields.Field pushToGUI(int position){
		position = goToPrisonData.getPrisonPosition();
		goToPrison = new desktop_fields.Jail.Builder().setBgColor(new Color(223f/255, 255f/255, 43f/255)).build();
		goToPrison.setDescription(this.getDescription());
		goToPrison.setTitle(goToPrisonData.getName());
		goToPrison.setSubText(getDescription());
		return goToPrison;
	}
	@Override
	public String getDescription() {
		
		return Translator.getString("GOTOPRISONDSC");
	}
	
	public String toString(){
		return "getDescription()=" + getDescription();
	}
}
