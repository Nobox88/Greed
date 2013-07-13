package se.roch0012.greed.model;

/**
 * The dices class store the representation of the dices
 * @author roch0012
 **/
public class Dices {
	int[] dices;

	public Dices(int numberOf){
		dices = new int[numberOf];
	}
	public void setDices(int[] dices) {
		this.dices = dices;
	}

	public void setDice(int i, int v) {
		dices[i-1] = v;
	}

	public int getDice(int i) {
		return dices[i-1];
	}

	public int[] getDices() {
		return dices;
	}
	
	public int getSize(){
		return dices.length;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[" + dices[0]);
		for (int i = 1; i < dices.length; i++){
			sb.append("," + dices[i]);
		}
		sb.append("]");
		return sb.toString();
	}	
}