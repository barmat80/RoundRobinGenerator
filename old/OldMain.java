package com.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import com.mb.model.Match;
import com.mb.model.Competitor;
import com.mb.model.TournamentMatches;

public class OldMain {
	private static LinkedList<Competitor> players;
	static {
		players = new LinkedList<>();
		players.add(new Competitor("Mattia Bargellini"));
		players.add(new Competitor("Mimmo"));
		players.add(new Competitor("Claudio Valente"));
		players.add(new Competitor("Fabio Tamma"));
		players.add(new Competitor("Cristian Zirondelli"));
		players.add(new Competitor("Federico Zanetti"));
		players.add(new Competitor("Francesco Gasperini"));
		players.add(new Competitor("Marco Brandolini"));
		players.add(new Competitor("Gianmarco Turi"));
		players.add(new Competitor("Riposo"));
	}

	private static LinkedList<Competitor> backupPlayers;

	public static void main(String args[]) {
		TournamentMatches seasonMatches = new TournamentMatches();

		reset(false);

		int matchDays = players.size() - 1;
		for(int md=1; md<=matchDays; md++) {
			log("MatchDay " + md);

			ArrayList<Match> matches = new ArrayList<>();
			for (int p = 1; p <= players.size() / 2; p++) {
				Match m = createNewMatch(md, seasonMatches);
				matches.add(m);
			}
			seasonMatches.put(md, matches);

			reset(true);
			log("*********************\n");
		}
	}

	private static void reset(boolean resetPlayers) {
		if(resetPlayers) {
			for (Competitor p : players) {
				p.setUnPicked();
			}
		}

		Competitor[] pp = players.toArray(new Competitor[0]);
		backupPlayers = new LinkedList<Competitor>(Arrays.asList(pp));
	}

	private static Match createNewMatch(int matchday, TournamentMatches sm) {
		Competitor p1 = getPlayer();
		Competitor p2 = getPlayer(p1);

		if (p1 != null && p2 != null) {
			Match match = new Match(p1, p2);
			if (sm.alreadyExists(matchday, match)) {
				return createNewMatch(matchday, sm);
			} else {
				log(p1.getName() + " vs " + p2.getName());

				backupPlayers.remove(findIndex(p1));
				backupPlayers.remove(findIndex(p2));

				p1.setPicked();
				p2.setPicked();

				return match;
			}
		} else {
			if (p1 == null) {
				log("No player 1 selected on matchday " + matchday);
			}

			if (p2 == null) {
				log("No player 2 selected on matchday " + matchday);
			}
			createNewMatch(matchday, sm);
		}
		return null;// non ci dovrebbe mai finire
	}

	private static Competitor getPlayer() {
		return getPlayer(null);
	}

	private static Competitor getPlayer(Competitor prevPlayer) {
		//		int numberOfAttempts = 0;
		Competitor p = null;
		boolean gotPlayer = false;
		do {
			int r = getRandom(players.size());
			if (prevPlayer != null && players.get(r).getName().equals(prevPlayer.getName())) {
				// un giocatore non può giocare contro se stesso
			} else {
				if (!players.get(r).isPicked()) {// se il giocatore non è ancora stato selezionato
					p = players.get(r);
					gotPlayer = true;
				} else {
					//					numberOfAttempts++;
				}
			}
		} while (!gotPlayer);// || numberOfAttempts < players.length);

		return p;
	}

	private static int findIndex(Competitor p) {
		int index = -1;
		for (int i = 0; i < backupPlayers.size(); i++) {
			if (backupPlayers.get(i).getName().equals(p.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}

	private static int getRandom(int len) {
		int rnd = ThreadLocalRandom.current().nextInt(len);
		return rnd;
	}

	private static void log(Object o) {
		System.out.println(o);
	}
}
