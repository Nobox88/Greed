package se.roch0012.greed.model;

import java.util.HashMap;

import se.roch0012.greed.handler.GreedPointCalculator;

/**
 * This class might be trivial
 * This class is trivial!!!
 * I know handles the points through sending int arrays
 * to PointCalculator
 * @author roch0012
 *
 */
public class Points {
	HashMap<Integer, Integer> pointMap = new HashMap<Integer, Integer>();
	GreedPointCalculator pointCalculator;

	Points(int size, GreedPointCalculator pc) {
		pointCalculator = pc;
		init(size);
	}

	public void setPoints(int[] d) {
		for (int i : d) {
			pointMap.put(i, pointMap.get(i) + 1);
		}
	}

	public int calculatePoints() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(pointMap);
		return 0; // TODO	
	}

	private void init(int n) {
		for (int i = 1; i <= n; i++) {
			pointMap.put(i, 0);
		}
	}

	public void resetPoints() {
		init(pointMap.size());
	}	
}
