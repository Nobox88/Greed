package se.roch0012.greed.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import se.roch0012.greed.handler.PointCalculator;

public class PointCalculatorTest {
	PointCalculator pc = new PointCalculator();
	@Test
	public void test() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(2);
		System.out.println(pc.calculate(list));
		
	}

}
