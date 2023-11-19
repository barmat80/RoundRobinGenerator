package com.bm.rrg.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.bm.rrg.Utilities;
import com.bm.rrg.model.Competitor;

public class Test {

	public static void main(String[] args) throws IOException {
		ArrayList<Competitor> c = Utilities.readTextFile(args[0]);
		c.add(new Competitor("Rest:", true));
		System.out.println(Arrays.toString(c.toArray()));
		Collections.shuffle(c);
		System.out.println(Arrays.toString(c.toArray()));

		System.out.println("\nBefore");

		int half = c.size() / 2;
		ArrayList<Competitor> homeCompetitors = sublist(c, 0, half);
		Collections.shuffle(homeCompetitors);
		System.out.println(Arrays.toString(homeCompetitors.toArray()));

		ArrayList<Competitor> awayCompetitors = sublist(c, half, c.size());
		Collections.shuffle(awayCompetitors);
		System.out.println(Arrays.toString(awayCompetitors.toArray()));

		System.out.println("\nAfter");
		for (int i = 0; i < 9; i++) {
			System.out.println(i);
			rotate(homeCompetitors, awayCompetitors);
			System.out.println(Arrays.toString(homeCompetitors.toArray()));
			System.out.println(Arrays.toString(awayCompetitors.toArray()) + "\n\n");
		}

	}

	private static ArrayList<Competitor> sublist(ArrayList<Competitor> baseList, int begin, int end) {
		ArrayList<Competitor> outList = new ArrayList<>();
		for (int i = begin; i < end; i++) {
			outList.add(baseList.get(i));
		}
		return outList;
	}

	private static void rotate(ArrayList<Competitor> homeCompetitors, ArrayList<Competitor> awayCompetitors) {
		// Extract the first element of the away list, remove it and add to the home
		// list as second element
		Competitor firstAwayComp = awayCompetitors.get(0);
		awayCompetitors.remove(firstAwayComp);
		homeCompetitors.add(1, firstAwayComp);

		// Extract the last element of the home list, remove it and add to the away list
		// as last element
		Competitor lastHomeComp = homeCompetitors.get(homeCompetitors.size() - 1);
		homeCompetitors.remove(lastHomeComp);
		awayCompetitors.add(lastHomeComp);
	}
}
