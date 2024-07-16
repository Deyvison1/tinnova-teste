package com.tinnova.teste.challenge;

public class TotalVotesVoters {
	private static final int totals = 1000;
	private static final int votesValid = 800;
	private static final int votesBrank = 150;
	private static final int votesNull = 50;

	public int percentageOfValidVotes() {
		return votesValid / totals;
	}

	public int percentageOfBlankVotes() {
		return votesBrank / totals;
	}

	public int percentageOfNullVotes() {
		return votesNull / totals;
	}
}
