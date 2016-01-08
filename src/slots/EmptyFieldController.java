package slots;

import game.Player;

public class EmptyFieldController extends Field{

	public EmptyFieldController(int i, Types type) {
		super(i, type);
		
	}

	@Override
	public void landOnField(Player player) {
		
		
	}

	@Override
	public String toString() {
		return "EmptyFieldController";
}
	
}