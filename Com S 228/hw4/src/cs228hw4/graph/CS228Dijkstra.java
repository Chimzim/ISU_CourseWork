package cs228hw4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class CS228Dijkstra<V> implements Dijkstra<V>{
	/**
	 *  @author Chimzim Ogbondah
	 *
	 * @param <V> the type of the objects being modeled as vertices in the graph
	 *            this algorithm will work on
	 *
	 * HashMap<V,Integer> distance - hashmap that will help calculate the shortest distance
	 * HashMap<V,V> predecessor - hashmap that helps keep track in the algorithm for the shortest path to each vertex
	 * ArrayList<V> visited - Tells if the vertex has been visited
	 * ArrayList<V> NotVisited - tells if the vertex has not been visited
	 * map - reference to DiGrpah which will allow be used in the constructor which takes in the graph
	 */
	private HashMap<V,Integer> distance;
	private HashMap<V,V> predecessor;
	private ArrayList<V> visited;
	private ArrayList<V> NotVisited;
	private DiGraph<V> map;
	
	
	
	public CS228Dijkstra(DiGraph<V> graph) {
		map = graph;
		distance = new HashMap<V,Integer>();
		predecessor = new HashMap<V,V>();
		visited = new ArrayList<V>();
		NotVisited = new ArrayList<V>();
	}
	 /**
	    * Uses Dijkstra's shortest path algorithm to calculate and store the 
	    * shortest paths to every vertex in the graph as well as the total costs
	    * of each of those paths.  This should run in O(E log V) time, where E is
	    * the size of the edge set, and V is the size of the vertex set.
	    * 
	    * @param start the vertex from which shortest paths should be calculated
	    */
	public void run(V start) {
		Iterator<V> itr = map.iterator();
		NotVisited.add(start);
		while(itr.hasNext()) {
			V vertex = itr.next();
			distance.put(vertex, Integer.MAX_VALUE);
			predecessor.put(vertex, null);
		}
		distance.replace(start,0);
		while(!NotVisited.isEmpty()) {
			int smallest = distance.get(NotVisited.get(0));
			V smallestV = null;
			for(int i = 0; i < NotVisited.size(); i++) {
				if(smallest > distance.get(NotVisited.get(i))) {
					smallest = distance.get(NotVisited.get(i));
					smallestV = NotVisited.get(i);
				}
			}
			visited.add(smallestV);
			NotVisited.remove(smallestV);
			for(V vertex: map.getNeighbors(smallestV)) {
				if(!visited.contains(vertex)) {
					int alt = distance.get(smallestV)+map.getEdgeCost(smallestV, vertex);
					if(distance.get(vertex) > alt) {
						distance.put(vertex, alt);
						predecessor.put(vertex, smallestV);
						NotVisited.add(vertex);
					}
				}
			}
		}
		
	}

	/**
	    * Retrieve, in O(V) time, the pre-calculated shortest path to the given 
	    * node. Using hash tables that give the predecessor (key is the current vertex the value is the vertex before)
	    * it goes backwards up the hash table until reaching a null value. since it will return the path backwards each vertex
	    * is added at position 0
	    * 
	    * @param vertex the vertex to which the shortest path, from the start 
	    *        vertex, is being requested
	    * @return a list of vertices comprising the shortest path from the start 
	    *         vertex to the given destination vertex, both inclusive
	    */
	@Override
	public List<V> getShortestPath(V vertex) {
		// TODO Auto-generated method stub
		ArrayList<V> path = new ArrayList<V>();
		path.add(vertex);
		V next = predecessor.get(vertex);
		while(predecessor.get(next) != null) {
			path.add(0,next);
			next = predecessor.get(vertex);
		}
		return path;
	}
	 /**
	    * Retrieve, in constant time, the shortest distance to the vertex using hashmaps it uses the key of the vertex
	    * in the parameter and returns the value associated to that vertex
	    * 
	    * @param vertex the vertex to which the cost of the shortest path, from the
	    *        start vertex, is desired
	    * @return the cost of the shortest path to the given vertex from the start
	    *         vertex or Integer.MAX_VALUE if there is path
	    */
	@Override
	public int getShortestDistance(V vertex) {
		// TODO Auto-generated method stub
		return distance.get(vertex);
	}

}
