package se.roch0012.greed.model;

/**
 * This will represent what state the dices have
 * @author roch0012
 **/

public class States {
	// o is for old
	// s is for selected
	// n is for not-selected
	public static final int OLD = 0;
	public static final int SELECTED = 1;
	public static final int NOT_SELECTED = 2;
	int[] states;

	public States(int size) {
		states = new int[size];
		for (int i = 0; i < states.length; i++) {
			states[i] = NOT_SELECTED;
		}

	}
	
	public void setStates(int[] states) {
		this.states = states;
	}

	public void setState(int i, int state) {
		states[i-1] = state;
	}
	
	public int[] getStates(){
		return states;
	}
	
	public int getState(int i){
		return states[i-1];
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[" + states[0]);
		for (int i = 1; i < states.length; i++){
			sb.append("," + states[i]);
		}
		sb.append("]");
		return sb.toString();
	}
}