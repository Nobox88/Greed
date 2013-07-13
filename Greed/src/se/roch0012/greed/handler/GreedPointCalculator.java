package se.roch0012.greed.handler;

import java.util.HashMap;

/**
 * To create a new set of rules regarding how the points
 * are given. This interface need to be implemented
 * @author roch0012
 *
 */
public interface GreedPointCalculator {
	public int calculate(int[] diceValues);

}
