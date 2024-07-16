package com.tinnova.teste.challenge;

public class SumMultiples {

	public int sum(int number) {
		int sum3 = 0;
		int sum5 = 0;

		for (int x = 0; number >= x; x++) {

			if (x % 3 == 0) {
				sum3 += x;
			}

			if (x % 5 == 0) {
				sum5 += x;
			}
		}
		return sum3 + sum5;
	}
}
