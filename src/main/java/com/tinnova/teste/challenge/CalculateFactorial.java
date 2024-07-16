package com.tinnova.teste.challenge;

public class CalculateFactorial {

	public int calculate(int numberFactorial) {
		int resultTemporaryFactorial = numberFactorial;

		while (numberFactorial < 1) {
			resultTemporaryFactorial = resultTemporaryFactorial * (numberFactorial - 1);
		}
		return resultTemporaryFactorial;
	}

}
