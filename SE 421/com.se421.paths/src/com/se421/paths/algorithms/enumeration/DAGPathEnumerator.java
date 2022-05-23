package com.se421.paths.algorithms.enumeration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.log.Log;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.se421.paths.algorithms.PathEnumerator;
import com.se421.paths.algorithms.PathCounter.CountingResult;
import com.se421.paths.support.HelperQueries;
import com.se421.paths.transforms.DAGTransform;

/**
 * Implement a program to enumerate paths in a program.
 * Paths must be enumerated in the specified canonical order
 * Please follow the enumeration algorithm given in the lecture notes
 * 
 * @author Chimzim Ogbondah
 */
public class DAGPathEnumerator extends PathEnumerator {
	List<Long> history = new ArrayList<Long>();
	// stack data structure for graph traversal.
	Stack<Node> stack = new Stack<Node>();
	// the total paths discovered
	List<List<Long>> paths = null;
	Q dag = null;
	AtlasSet<Node> dagLeaves = null;
	//Bf flag
	String flag = "BFLAG";

	public DAGPathEnumerator() {}
	
	public CountingResult countPaths(Q cfg){
		try {
			enumeratePaths(cfg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new CountingResult(paths.size());
	}

	/**
	 * Enumerates each path in the given CFG and returns each
	 * path as a list of line numbers.
	 * @throws IOException 
	 */
	@Override
	public EnumerationResult enumeratePaths(Q cfg) throws IOException {
		// the total number of paths discovered
		paths = new ArrayList<List<Long>>();

		// create a directed acyclic graph (DAG)
		DAGTransform transformer = new DAGTransform();
		dag = transformer.transform(cfg);

		// the roots and leaves of the DAG
		dagLeaves = dag.leaves().eval().nodes();
		Node dagRoot = dag.roots().eval().nodes().one();
		
		// handle some trivial edge cases
				if(dagRoot == null) {
					// function is empty, there are no paths
					return new EnumerationResult(paths);
				} else if(dagLeaves.contains(dagRoot)) {
					// function contains a single node there must be 1 path
					ArrayList<Long> path = new ArrayList<Long>();
					path.add(getLineNumber(dagRoot));
					return new EnumerationResult(paths);
				}
				
			   forwardPass(dagRoot);
	

		// Write results to a file
		String function = HelperQueries.getContainingFunction(dagRoot).getAttr(XCSG.name).toString();
		String name = System.getProperty("user.home") + "/Desktop/Paths-" + function + ".txt";
		BufferedWriter w = new BufferedWriter(new FileWriter(name));
		paths.forEach(path -> {try {
			w.write(path.toString() + "\n");
		} catch (IOException e) {
			Log.info("File not found");
		}});
		w.close();
		return new EnumerationResult(paths);
	}
	
	/**
	 * @param node
	 * Follow the lecture note to know more about it
	 */
	void forwardPass(Node node) {
		// Example on how to get line numbers for a node
		long lineNumber = getLineNumber(node);
		//appends the line number corresponds to the node
		history.add(lineNumber);
		stack.push(node);
		if(isLeafNode(node)) {
			// since we reached a leaf we know we have a new path
			// update the history
			List<Long> path = new ArrayList<Long>(history);

			// add the complete path
			paths.add(path);
			//TODO
			backwardPass(node);
		} else {
			if(isBranchNode(node)) {
				//TODO	
				Edge trueEdge = getTrueEdge(node);
				Node successor = trueEdge.to();
				node.putAttr(flag, "1");
				forwardPass(successor);
			} else {
				Node successor = dag.successors(Common.toQ(node)).eval().nodes().one();
				forwardPass(successor);
				//TODO
			}
		}
		
		
	}
	
	/**
	 * @param node
	 * Follow the lecture note to know more about it
	 */
	void backwardPass(Node node) {
		if(isBranchNode(node)) {
			//TODO
			//String Flag = node.getAttr(flag).toString();
			if(node.getAttr(flag).toString().equals("0")) {
				stack.pop();
				history = history.subList(0,  history.size()-1);
				Node predecessor = stack.peek();
				backwardPass(predecessor);
			}else {
				node.putAttr(flag, "0");
				Edge falseEdge = getFalseEdge(node);
				Node successor = falseEdge.to();
				forwardPass(successor);
			}
		} else {
			if(isRootNode(node)){
				return;
			}
			stack.pop();
			history = history.subList(0, history.size()-1);
			Node predecessor = stack.peek();
			backwardPass(predecessor);
			//TODO
		}
	}
	
	/**
	 * @param node
	 * @return true if node is a leaf node else false
	 */
	boolean isLeafNode(Node node) {
		if(dagLeaves.contains(node)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param node
	 * @return true if node is a branch node else false
	 */
	boolean isBranchNode(Node node) {
		if(node.taggedWith(XCSG.ControlFlowCondition)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param node
	 * @return true of node is a root node else false
	 */
	boolean isRootNode(Node node) {
		if(node.taggedWith(XCSG.controlFlowRoot)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param conditionalNode
	 * @return 1-edge of conditionalNode
	 * you may need it to get 1-successor of conditionalNode
	 */
	Edge getTrueEdge(Node conditionalNode) {
		Q outgoingEdges = dag.forwardStep(Common.toQ(conditionalNode));
		Q trueEdges = outgoingEdges.selectEdge(XCSG.conditionValue, true, "true").retainEdges();
		return trueEdges.eval().edges().one();
	}
	
	/**
	 * @param conditionalNode
	 * @return 0-edge of conditionalNode.
	 * you may need it to get 0-successor of conditionalNode
	 */
	Edge getFalseEdge(Node conditionalNode) {
		Q outgoingEdges = dag.forwardStep(Common.toQ(conditionalNode));
		Q falseEdges = outgoingEdges.selectEdge(XCSG.conditionValue, false, "false").retainEdges();
		return falseEdges.eval().edges().one();
	}
}
