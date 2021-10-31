package com.mb.model;

/**
 * Represents a match between two competitors
 *
 * @author mattia
 *
 */
public class Match {
	private Competitor c1;
	private Competitor c2;

	public Match(Competitor cc1, Competitor cc2) {
		c1 = cc1;
		c2 = cc2;
	}

	public Competitor getCompetitor1() {
		return c1;
	}

	public Competitor getCompetitor2() {
		return c2;
	}
}
