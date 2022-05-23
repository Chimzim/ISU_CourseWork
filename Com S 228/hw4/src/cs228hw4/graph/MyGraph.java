package cs228hw4.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MyGraph<V> implements DiGraph<V>{

	@Override
	public Set<? extends V> getNeighbors(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEdgeCost(V start, V end) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<V> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
/*	private ArrayList<vertex> myVertex;
	private ArrayList<Edge> myEdges;
	private class vertex {
		private String id;
		private V data;
		public vertex(String vid, V D) {
			id = vid;
			data = D;
		}
		public boolean equals(Object o) {
			vertex other = (vertex) o;
			return id.contentEquals(other.id);
			
		}
	}
	
	private class Edge {
		private String id1, id2;
		private V data;
		private int edgeCost;
		
		public Edge(String Eid1, String Eid2, int cost) {
			id1 = Eid1;
			id2 = Eid2;
			edgeCost = cost;
		}
	}
	
	@Override
	public Set<? extends V> getNeighbors(V vertex) {
		Set<? extends V> neighbors = new ArrayList<vertex>();
		// TODO Auto-generated method stub
		for(Edge e: myEdges) {
			if(e.id1.equals(vertex)) {
				neighbors.addAll(myVertex.get(myVertex.indexOf(e.id2)));
			}
		}
		return neighbors;
	}

	@Override
	public int getEdgeCost(V start, V end) {
		// TODO Auto-generated method stub
		Queue<vertex> q = new LinkedList<vertex>();
		ArrayList<vertex> explored = new ArrayList<>();
		int edgeCost = 0;
		int found = 0;
		for(int i = 0; i < myVertex.size(); i++) {
			if(myVertex.get(i).equals(start)) {
				//found = 1;
				q.add(myVertex.get(i));
				break;
			}
		}
		while(!q.isEmpty()) {
			vertex current = q.remove();
			if(current.equals(end)) {
				break;
			}
			else {
				for(Edge e: myEdges) {
					explored.add(current);
					if(current.id.equals(e.id1) && !explored.contains(myVertex.get(myVertex.indexOf(e.id2)))) {
						q.add(myVertex.get(myVertex.indexOf(e.id2)));
						edgeCost += e.edgeCost;
					}
				}
			}
		}
		
		return edgeCost;
	}

	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return myVertex.size();
	}

	@Override
	public Iterator<V> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
*/
}
