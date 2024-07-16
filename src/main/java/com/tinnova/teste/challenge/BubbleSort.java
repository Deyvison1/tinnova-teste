package com.tinnova.teste.challenge;

public class BubbleSort {
	int aux = 0;
	int i = 0;

	public int[] sort(int array[]) {

		for (i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				if (array[j] > array[j + 1]) {
					aux = array[j];
					array[j] = array[j + 1];
					array[j + 1] = aux;
				}
			}
		}

		return array;
	}
}
