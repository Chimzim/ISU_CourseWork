package graph;

import java.util.*;

/**
 * 
 * @author Xiaoqiu Huang
 * 
 * An undirected graph class implemented using a hash map whose keys are
 * vertices of type V and whose values are sets of neighbors (HashSet of V).
 * a
 * Neither parallel edges or self-loops are allowed.
 * 
 * This class is complete and should not be modified
 */
public class Graph<V> {
    // map : key = vertex, value = set of neighboring vertices
    private HashMap<V, HashSet<V>> map;

    // number of edges
    private int edgeCount;

    /**
     * Creates an empty graph
     */
    public Graph() {
        map = new HashMap<V, HashSet<V>>();
    }

    /**
     * Return number of vertices
     */
    public int numV() {
        return map.keySet().size();
    }

    /**
     * Return number of edges
     */
    public int numE() {
        return edgeCount;
    }

    /**
     * Return the degree of vertex
     * 
     * @param v
     * - the data of the vertex to get the degree of
     * 
     * @return the degree of v, 0 if v does not exist in the Graph
     */
    public int degree(V v) {
        if (!map.containsKey(v))
            return 0;
        else
            return map.get(v).size();
    }

    /**
     * Adds an undirected edge between vertices u and v adding new vertices they
     * do not exist in the Graph already.
     * 
     * @param u
     * - the data of the first vertex
     * 
     * @param v
     * - the data of the second vertex
     * 
     * @throws NullPointerException
     * - if u or v is null
     * 
     * @throws IllegalArgumentException
     * - if u.equals(v) is true
     */
    public void addEdge(V u, V v) {
        if (u == null || v == null)
            throw new NullPointerException("Vertices may not be null.");

        if (u.equals(v))
            throw new IllegalArgumentException("Self loops are not allowed.");

        addVertex(u);
        addVertex(v);

        if (!hasEdge(u, v))
            edgeCount++;

        map.get(u).add(v);
        map.get(v).add(u);
    }

    /**
     * Add a new vertex with no neighbors (if it does not yet exist)
     * 
     * @param v
     * - the data of the new vertex
     */
    public void addVertex(V v) {
        if (hasVertex(v))
            return;

        map.put(v, new HashSet<V>());
    }

    /**
     * Returns an Iterable set of all vertices in the Graph
     */
    public Iterable<V> vertices() {
        return map.keySet();
    }

    /**
     * Returns an Iterable set of the neighbors of the given vertex
     * 
     * @param v
     * - the vertex to to Iterate over
     * @return an Iterator over the neighbors of v, Iterator over an empty set
     * if v does not exist in the Graph
     */
    public Iterable<V> adjacentTo(V v) {
        // return empty set iterator if v isn't in graph
        if (!hasVertex(v))
            return new HashSet<V>();
        else
            return map.get(v);
    }

    /**
     * Test if a vertex in the graph
     * 
     * @param v
     * - the data of the vertex to search for
     * @return true if v was found, false otherwise
     */
    public boolean hasVertex(V v) {
        return map.containsKey(v);
    }

    /**
     * Test if (u, v) an edge in the graph
     * 
     * @param u
     * - the data of the first vertex in the Edge
     * @param v
     * - the data of the second vertex in the Edge
     * @return true if (u, v) exists in the Graph, false otherwise
     */
    public boolean hasEdge(V u, V v) {
        if (!hasVertex(u))
            return false;

        return map.get(u).contains(v);
    }

    /**
     * Removes the given vertex from the Graph
     * 
     * @param v
     * - that data of the vertex to remove
     */
    public void removeVertex(V v) {
        if (!hasVertex(v))
            return;

        edgeCount -= degree(v);

        // Remove v from the vertex list
        map.remove(v);

        // Remove all incoming edges to v
        for (V other : map.keySet())
            map.get(other).remove(v);
    }

    /**
     * Removes the given edge from the Graph
     * 
     * @param u
     * - the data of the first vertex in the Edge
     * @param v
     * - the data of the second vertex in the Edge
     */
    public void removeEdge(V u, V v) {
        if (!hasVertex(u) || !hasVertex(v))
            return;

        map.get(u).remove(v);
        map.get(v).remove(u);

        edgeCount--;
    }

    /**
     * Returns a String representation of the Graph - Not very efficient,
     * intended for debugging
     */
    public String toString() {
        String s = "";
        for (V f : map.keySet()) {
            s += f.toString() + ": ";
            for (V e : map.get(f)) {
                s += e.toString() + " ";
            }
            s += "\n";
        }
        return s;
    }
}
