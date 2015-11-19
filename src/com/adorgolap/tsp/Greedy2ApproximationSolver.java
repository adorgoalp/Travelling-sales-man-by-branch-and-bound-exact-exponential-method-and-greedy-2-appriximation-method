package com.adorgolap.tsp;

import java.util.ArrayList;

public class Greedy2ApproximationSolver {
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	ArrayList<Vertex> mst = new ArrayList<Vertex>();
	public Greedy2ApproximationSolver() {
		vertices = Helper.takeInput();
		long startTime = System.currentTimeMillis();
		mst = Helper.getMST(vertices);
//		System.out.println("MST "+mst);
		Helper.result.clear();
		ArrayList< Vertex> preOrderTraversat = Helper.preOrder(mst, 0);
//		System.out.println(preOrderTraversat);
		double pathCost = Helper.findPathCostForList(preOrderTraversat);
//		System.out.println(pathCost);
		long timeTaken = System.currentTimeMillis()-startTime;
		
		System.out.println("Greedy 2 approximation solver");
		System.out.println(pathCost);
//		System.out.println("Time taken " + timeTaken+" mili seconds.\n");
//		Helper.output("Greedy2_approximation_1005027", vertices.size(),timeTaken);
		Helper.outputApproximateRatio("ar", pathCost+"\n");
	}
}
