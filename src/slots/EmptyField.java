package slots;

import game.Player;

public class EmptyField extends Field{

	public EmptyField(int i, Types type) {
		super(i, type);
		
	}

	@Override
	public void landOnField(Player player) {
		
		
	}

	@Override
	public String toString() {
		return "EmptyField";
}
	
}
