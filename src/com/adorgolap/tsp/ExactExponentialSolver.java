package com.adorgolap.tsp;
import java.util.ArrayList;

public class ExactExponentialSolver {
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	public ExactExponentialSolver() {
		vertices = Helper.takeInput();
		long startTime = System.currentTimeMillis();
		ArrayList<ArrayList<Vertex>> r = Helper.getPermutations(vertices);
		ArrayList<Double> pathCost = new ArrayList<Double>();
		
		for (ArrayList<Vertex> list : r) {
			pathCost.add(Helper.findPathCostForList(list));
		}
		int minIndex = 0;
		for(int i = 0 ; i < pathCost.size();i++)
		{
			if(pathCost.get(i)<pathCost.get(minIndex))
			{
				minIndex = i;
			}
		}
		ArrayList<Vertex> tsp = r.get(minIndex);
//		for(Vertex v : tsp)
//		{
//			System.out.println(v);
//		}
//		System.out.println("Total cost "+pathCost.get(minIndex));
		long totalTime = System.currentTimeMillis()-startTime;
//		System.out.println("Time taken "+(stopTime-startTime)+" mili seconds.\n\n");
		System.out.println("Exact Exponential Solver");
		System.out.println(tsp.size());
		Helper.output("Exact_output_1005027",tsp.size(), totalTime);
	}

	
}

