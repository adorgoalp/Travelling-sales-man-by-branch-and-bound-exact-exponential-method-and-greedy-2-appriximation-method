package com.adorgolap.tsp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class Helper {
	public static ArrayList<Vertex> takeInput() {
		ArrayList<Vertex> vertexs = new ArrayList<Vertex>();
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader("input.txt"));
			while ((line = br.readLine()) != null) {
				String[] t = line.split(",");
				double x = Double.parseDouble(t[0]);
				double y = Double.parseDouble(t[1]);
				Vertex v = new Vertex(x, y);
				vertexs.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return vertexs;
	}

	public static ArrayList<ArrayList<Vertex>> getPermutations(ArrayList<Vertex> original) {
		if (original.size() == 0) {
			ArrayList<ArrayList<Vertex>> result = new ArrayList<ArrayList<Vertex>>();
			result.add(new ArrayList<Vertex>());
			return result;
		}
		Vertex firstElement = original.remove(0);
		ArrayList<ArrayList<Vertex>> returnValue = new ArrayList<ArrayList<Vertex>>();
		ArrayList<ArrayList<Vertex>> permutations = getPermutations(original);
		for (ArrayList<Vertex> smallerPermutated : permutations) {
			for (int index = 0; index <= smallerPermutated.size(); index++) {
				ArrayList<Vertex> temp = new ArrayList<Vertex>(smallerPermutated);
				temp.add(index, firstElement);
				returnValue.add(temp);
			}
		}
		return returnValue;
	}

	// public static ArrayList<Vertex> getMST(ArrayList<Vertex> vertices) {
	// ArrayList<Vertex> result = new ArrayList<Vertex>();
	// double[][] distanceTable = new double[vertices.size()][vertices.size()];
	// for (int i = 0; i < vertices.size(); i++) {
	// for (int j = i; j < vertices.size(); j++) {
	// distanceTable[i][j] = distanceTable[j][i] =
	// vertices.get(i).getDistance(vertices.get(j));
	// }
	// }
	// Vertex parent = null;
	// int nextNode = 0;
	// while(true)
	// {
	// Vertex currentVertex = vertices.get(nextNode);
	// currentVertex.parent = parent;
	// parent = currentVertex;
	// result.add(currentVertex);
	// for(int i = 0 ; i < vertices.size() ; i++)
	// {
	// distanceTable[i][nextNode] = Double.MAX_VALUE;
	// }
	// if(vertices.size() == result.size())
	// {
	// break;
	// }
	// int minIndex = 0;
	// for(int i = 0 ; i < vertices.size() ; i++)
	// {
	// if(i!= minIndex && distanceTable[nextNode][i] <
	// distanceTable[nextNode][minIndex])
	// {
	// minIndex = i;
	//
	// }
	// }
	// nextNode = minIndex;
	// }
	// return result;
	//
	// }
	public static ArrayList<Vertex> getMST(ArrayList<Vertex> vertices) {
		ArrayList<Integer> visited = new ArrayList<>();
		ArrayList<Vertex> result = new ArrayList<Vertex>();
		double[][] distanceTable = new double[vertices.size()][vertices.size()];
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = i; j < vertices.size(); j++) {
				distanceTable[i][j] = distanceTable[j][i] = vertices.get(i).getDistance(vertices.get(j));
			}
		}
		Vertex currentVertex = vertices.get(0);
		currentVertex.parent = null;
		result.add(currentVertex);
		visited.add(0);
		int minIndex = 0;
		int parentIndex = 0;
		while (result.size() != vertices.size()) {
			double minDistance = Double.MAX_VALUE;
			for (int row : visited) {
				for (int column = 0; column < vertices.size(); column++) {
					if (!visited.contains(column)) {
						if (distanceTable[row][column] < minDistance) {
							minIndex = column;
							minDistance = distanceTable[row][column];
							parentIndex = row;
						}
					}
				}
			}
			// got the minimum distanced unvisited node at this point
			Vertex parent = vertices.get(parentIndex);
			currentVertex = vertices.get(minIndex);
			currentVertex.parent = parent;
			parent.children.add(currentVertex);
			visited.add(minIndex);
			result.add(currentVertex);
		}
		return result;
	}

	static public ArrayList<Vertex> result = new ArrayList<Vertex>();

	public static ArrayList<Vertex> preOrder(ArrayList<Vertex> mst, int root) {
		result.add(mst.get(root));
//		System.out.print(mst.get(root)+", ");
		for (Vertex c : mst.get(root).children) {
			preOrder(mst, mst.indexOf(c));
			result.add(c);
		}
		// System.out.println(" p o "+ result);
		return result;

	}

	public static Double findPathCostForList(ArrayList<Vertex> list) {
		double totalDistance = 0;
		for (int i = 0; i < list.size(); i++) {
			int f = i;
			int s = i + 1;
			if (s == list.size()) {
				s = 0;
			}
			totalDistance += list.get(f).getDistance(list.get(s));
		}
		return totalDistance;
	}

	public static void createInput(int n) {
		Random random = new Random();
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("input.txt"), "utf-8"))) {
			for (int i = 0; i < n; i++) {
				double x = random.nextInt(300) / 10.0;
				double y = random.nextInt(300) / 10.0;
				writer.write(x + "," + y + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void output(String destinationFileName,int inputSize, double time) {
		File file = new File(destinationFileName+".csv");
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(inputSize + ", " + time+"\n");
			bufferWritter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void outputApproximateRatio(String destinationFileName,String val) {
		File file = new File(destinationFileName+".csv");
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(val);
			bufferWritter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
