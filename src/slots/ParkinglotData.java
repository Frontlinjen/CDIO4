package slots;

import java.awt.Color;

import desktop_resources.GUI;
import game.Player;
import game.Translator;

public class ParkinglotData extends FieldData{


	private int bonus;
	
	
	
	public ParkinglotData(int i, int bonus) {
		super(i);
		this.bonus = bonus;
	}

	
	
	
	@Override
	public String toString() {
		return "ParkingLot [parkingLot=" + parkingLot + ", bonus=" + bonus + ", position=" + position
				+ ", getName()=" + getName() + ", getPosition()=" + getPosition() + "]";
	}
}
