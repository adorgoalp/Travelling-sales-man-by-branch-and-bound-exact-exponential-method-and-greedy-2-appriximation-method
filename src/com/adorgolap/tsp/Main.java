package com.adorgolap.tsp;

public class Main {
	public static void main(String[] args) {
		for (int i = 2; i < 31; i++) {
			Helper.createInput(i);
			System.out.println(i);
			new BranchAndBoundingSolver();
			new Greedy2ApproximationSolver();
		}
	}
}
