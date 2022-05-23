package com.se421.paths.algorithms.counting;

import java.util.Stack;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.se421.paths.algorithms.PathCounter;
import com.se421.paths.transforms.DAGTransform;

/**
 * This program counts all paths in a CFG by enumerating each path.
 * 
 * Use this implementation to verify the number of paths in any cfg. 
 * @author Sharwan
 */
public class DAGPathCounter extends PathCounter {

	public DAGPathCounter() {}

	/**
	 * Counts the number of paths in a given CFG
	 *
	 * Example Atlas Shell Usage:
	 * var dskqopt = functions("dskqopt")
	 * var dskqoptCFG = cfg(dskqopt)
	 * var dfsCounter = new DFSPathCounter
	 * dfsCounter.countPaths(dskqoptCFG)
	 *
	 * @param cfg
	 * @return
	 */
	public CountingResult countPaths(Q cfg) {
		// the total number of paths discovered
		// and the number of additions required to count the path
		long numPaths = 0;

		// create a directed acyclic graph (DAG)
		DAGTransform transformer = new DAGTransform();
		Q dag = transformer.transform(cfg);

		// the roots and leaves of the DAG
		AtlasSet<Node> dagLeaves = dag.leaves().eval().nodes();
		Node dagRoot = dag.roots().eval().nodes().one();

		// handle some trivial edge cases
		if(dagRoot == null) {
			// function is empty, there are no paths
			return new CountingResult(0L);
		} else if(dagLeaves.contains(dagRoot)) {
			// function contains a single node there must be 1 path
			return new CountingResult(1L);
		}
		 // stack data structure for graph traversal.
		Stack<Node> stack = new Stack<Node>();

		// start traversing from the root
		stack.push(dagRoot);

		 // Start Traversing the DAG
		while (!stack.isEmpty()) {
			// next node to process
			Node currentNode = stack.pop();

			// get the children of the current node
			// note: we iterate by edge in case there are multiple edges from a predecessor to a successor
			for (Edge outgoingEdge : dag.forwardStep(Common.toQ(currentNode)).eval().edges()) {
				Node successor = outgoingEdge.to();
				if(dagLeaves.contains(successor)) {
					// if we reached a leaf increment the counter by 1
					numPaths++;
				} else {
					// push the child node on the stack to be processed
					stack.push(successor);
				}
			}
		}
		// at the end, we have traversed all paths once, so return the count
		return new CountingResult(numPaths);
	}

}
