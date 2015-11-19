package com.adorgolap.tsp;

import java.util.ArrayList;

public class BranchAndBoundingSolver {
	int startVertexIndex = 0;
	ArrayList<Vertex> vertices = new ArrayList<Vertex>();

	public BranchAndBoundingSolver() {
		vertices = Helper.takeInput();
		long startTime = System.currentTimeMillis();
		ArrayList<Integer> visitedSoFar = new ArrayList<Integer>();
		visitedSoFar.add(startVertexIndex);
		TSP(startVertexIndex, 0, visitedSoFar);
		long totalTime = System.currentTimeMillis() - startTime;
//		System.out.println(bestSoFar);
//		System.out.println("Total time taken " + totalTime + " mili seconds. \n\n");
		System.out.println("Brunch and Bounding Solver");
		System.out.println(bestSoFar);
		Helper.outputApproximateRatio("ar", bestSoFar+",");
	}

	private double bestSoFar = Double.MAX_VALUE;
	int x = 0;
	private void TSP(int lastNode, double costSoFar, ArrayList<Integer> visitedSoFar) {
		if (visitedSoFar.size() == vertices.size()) {
			double loopFininshingCost = vertices.get(startVertexIndex).getDistance(vertices.get(lastNode));
			if (costSoFar + loopFininshingCost < bestSoFar) {
				bestSoFar = costSoFar + loopFininshingCost;
				return;
			}
			return;
		}
		double approximateCost = costSoFar + costStartToVminusS(visitedSoFar)
				+ costVminusSToLastNode(lastNode, visitedSoFar) + costMSTofVminusS(visitedSoFar);
		if (approximateCost < bestSoFar) {
			for (Vertex v : vertices) {
				int currentNodeIndex = vertices.indexOf(v);
				if (!visitedSoFar.contains(currentNodeIndex)) {
					double cost = costSoFar + costStothisVertex(currentNodeIndex,lastNode);
					visitedSoFar.add(currentNodeIndex);
					TSP(currentNodeIndex, cost, visitedSoFar);
//					System.out.println(x++ + " "+visitedSoFar+ " c n i "+currentNodeIndex);
					visitedSoFar.remove(visitedSoFar.indexOf(currentNodeIndex));
				}
			}
		}
		return;
	}

	private double costStothisVertex(int currentNodeIndex, int lastNode) {
		
		return vertices.get(lastNode).getDistance(vertices.get(currentNodeIndex));
	}

	private double costVminusSToLastNode(int lastNode, ArrayList<Integer> visitedSoFar) {
		ArrayList<Double> costs = new ArrayList<Double>();
		for (int i = 0; i < vertices.size(); i++) {
			if (!visitedSoFar.contains(i)) {
				costs.add(vertices.get(lastNode).getDistance(vertices.get(i)));
			}
		}
		double lowestCost = costs.get(0);
		for (double c : costs) {
			if (c < lowestCost) {
				lowestCost = c;
			}
		}
		return lowestCost;
	}

	private double costStartToVminusS(ArrayList<Integer> visitedSoFar) {
		ArrayList<Double> costs = new ArrayList<Double>();
		for (int i = 0; i < vertices.size(); i++) {
			if (!visitedSoFar.contains(i)) {
				costs.add(vertices.get(startVertexIndex).getDistance(vertices.get(i)));
			}
		}
		double lowestCost = costs.get(0);
		for (int i = 0; i < costs.size(); i++) {
			if (costs.get(i) < lowestCost) {
				lowestCost = costs.get(i);
			}
		}
		return lowestCost;
	}

	private double costMSTofVminusS(ArrayList<Integer> visitedSoFar) {
		ArrayList<Vertex> unvisited = new ArrayList<Vertex>();
		for(int i = 0 ; i < vertices.size();i++)
		{
			if(!visitedSoFar.contains(i))
			{
				Vertex v = new Vertex(vertices.get(i).x, vertices.get(i).y);
				unvisited.add(v);
			}
		}
		ArrayList<Vertex> mst = Helper.getMST(unvisited);
		return Helper.findPathCostForList(mst);
	}
}
