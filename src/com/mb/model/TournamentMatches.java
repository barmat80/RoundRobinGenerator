package com.mb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains all the matches of the tournament
 *
 * @author mattia
 *
 */

public class TournamentMatches {
	private HashMap<Integer, ArrayList<Match>> tournament;

	public TournamentMatches() {
		tournament = new HashMap<>();
	}

	public void put(int matchday, ArrayList<Match> matches) {
		tournament.put(matchday, matches);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean alreadyExists(int matchday, Match match) {
		Competitor p1 = match.getCompetitor1();
		Competitor p2 = match.getCompetitor2();

		boolean found = false;
		// Look for any occurrences of this match in all the previous days
		for (Map.Entry me : tournament.entrySet()) {
			Integer day = (Integer) me.getKey();
			ArrayList<Match> matches = (ArrayList<Match>)me.getValue();
			if (day < matchday) {
				for (Match m : matches) {
					if (m.getCompetitor1().equals(p1) && m.getCompetitor2().equals(p2)
							|| m.getCompetitor1().equals(p2) && m.getCompetitor2().equals(p1)) {
						found = true;
						break;
					}
				}
			}

			if (found) {
				break;
			}
		}
		return found;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry me : tournament.entrySet()) {
			Integer day = (Integer) me.getKey();
			sb.append("Matchday ").append(day).append("\n");

			ArrayList<Match> matches = (ArrayList<Match>)me.getValue();
			String restCmpt = "";
			for (Match m : matches) {
				String c1 = m.getCompetitor1().getName();
				String c2 = m.getCompetitor2().getName();

				if(c1.equalsIgnoreCase("rest") || c2.equalsIgnoreCase("rest")) {
					if (c1.equalsIgnoreCase("rest")) {
						restCmpt = "Rest: " + c2;
					} else {
						restCmpt = "Rest: " + c1;
					}
				} else {
					sb.append(c1);
					sb.append(" v ");
					sb.append(c2);
					sb.append("\n");
				}
			}

			if (!restCmpt.isEmpty()) {
				sb.append(restCmpt).append("\n");
			}

			sb.append("\n");
		}

		return sb.toString();
	}
}
