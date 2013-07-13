package se.roch0012.greed.handler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is where the rules regarding the points are hardcoded
 * This calculates the points depending on the rules
 * @author roch0012
 * 
 */

public class PointCalculator implements GreedPointCalculator {

	@Override
	public int calculate(int[] diceValues) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int n : diceValues) {
			if (map.get(n) == null)
				map.put(n, 0);
			map.put(n, map.get(n) + 1);
		}

		return calc(map);
	}
	
	
	// TODO: Will need to debug this, it couses null pointers.
	public int calculate(ArrayList<Integer> diceValues){
		int[] intArray = new int[diceValues.size()];
	    for (int i=0; i < intArray.length; i++)
	    {
	        intArray[i] = diceValues.get(i).intValue();
	    }
	    
	    return calculate(intArray);
	}

	private int calc(HashMap<Integer, Integer> map) {
		int score = 0;

		score += straight(map);
		score += thrice(map);
		score += singles(map);

		return score;
	}

	// Gives points if singles (only 1 and 5) exists
	private int singles(HashMap<Integer, Integer> map) {
		// this will be recursive

		if (map.get(1) != null && map.get(1) > 0) {
			map.put(1, map.get(1) - 1);
			return 100 + singles(map);
		}
		if (map.get(5) != null && map.get(5) > 0) {
			map.put(5, map.get(1) - 1);
			return 50 + singles(map);
		}
		return 0;
	}

	// Give points if three of a kind exists
	private int thrice(HashMap<Integer, Integer> map) {
		// this will be recursive
		for (int i : map.keySet()) {
			if (map.get(i) >= 3) {
				map.put(i, map.get(i) - 3);
				if (i == 1)
					return 1000 + thrice(map);
				else
					return ((100 * i) + thrice(map));
			}
		}
		return 0;
	}

	// Gives points if a straight exists
	private int straight(HashMap<Integer, Integer> map) {
		for (int i = 1; i <= 6; i++) {
			if (map.get(i) == null || map.get(i) < 1)
				return 0;
		}
		for (int i = 1; i <= 6; i++)
			if (map.get(i) != null)
				map.put(i, map.get(i) - 1);
		return 1000;

	}

}
