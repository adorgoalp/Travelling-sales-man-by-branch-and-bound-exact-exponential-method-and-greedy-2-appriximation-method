package com.adorgolap.tsp;

import java.util.ArrayList;

public class Vertex {
	double x;
	double y;
	Vertex parent;
	ArrayList<Vertex> children = new ArrayList<Vertex>();
	public Vertex(double x,double y) {
		this.x = x;
		this.y = y;
	}
	public double getDistance(Vertex v) {
		return Math.sqrt((this.x-v.x)*(this.x-v.x) + (this.y-v.y)*(this.y-v.y));

	}
	public void setParent(Vertex parent) {
		this.parent = parent;
	}
	public Vertex getParent() {
		return parent;
	}
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
