package com.se421.paths.algorithms.counting;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.log.Log;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.se421.paths.algorithms.PathCounter;
import com.se421.paths.transforms.DAGTransform;

/**
 * This program counts all paths in a CFG
 * This implementation runs in O(n) time.
 * Please follow the multiplicity algorithm given in the lecture notes
 * 
 * @author Chimzim Ogbondah
 */
public class MultiplicitiesPathCounter extends PathCounter {
	
	Map<Node,Long> multiplicities;
	//Bf flag 
	String bflag = "BFLAG";
	//Rf flag
	String rflag = "RFLAG";

	Q dag = null;
	
	AtlasSet<Node> dagLeaves = null;
	
	// stack data structure for graph traversal.
	Stack<Node> stack;
	long counter =0L;
	
	public MultiplicitiesPathCounter() {}

	/**
	 * Counts the number of paths in a given CFG
	 * 
	 * Example Atlas Shell Usage:
	 * var dskqopt = functions("dskqopt")
	 * var dskqoptCFG = cfg(dskqopt)
	 * var mCounter = new MultiplicitiesPathCounter
	 * mCounter.countPaths(dskqoptCFG)
	 * 
	 * @param cfg
	 * @return
	 */
	public CountingResult countPaths(Q cfg) {
		multiplicities = new HashMap<Node,Long>();
		counter = 0L;
		stack = new Stack<Node>();
		// create a directed acyclic graph (DAG)
		DAGTransform transformer = new DAGTransform();
		dag = transformer.transform(cfg);
		
		// the roots and leaves of the DAG
		dagLeaves = dag.leaves().eval().nodes();
		Node dagRoot = dag.roots().eval().nodes().one();

		// handle some trivial edge cases
				if(dagRoot == null) {
					// function is empty, there are no paths
					return new CountingResult(0L);
				} else if (dagLeaves.contains(dagRoot)) {
					// function contains a single node there must be 0 path
					return new CountingResult(1L);
				}
				
				// initialize multiplicity for every node to 0, except the root,
				// which is initialized to a value of 1
				//Also initialize Rf value for every node.
				for(Node node : dag.eval().nodes()) {
					if(node.equals(dagRoot)) {
						multiplicities.put(node, 1L);
						node.putAttr(rflag, 0);
					} else {
						multiplicities.put(node, 0L);
						node.putAttr(rflag, dag.reverseStep(Common.toQ(node)).eval().edges().size());
					}
				}
				
				 Log.info("I will finish this project");
				
				 forwardPass(dagRoot);
				 
				 //total path count is the sum of the multiplicity of all leaves
				 for(Node node: dagLeaves) {
					 counter = counter + multiplicities.get(node);
				 }
		
		// at the end, we have traversed all paths once, so return the count
		return new CountingResult(counter);
	}
	
	/**
	 * @param node
	 * Follow lecture notes to know more about it
	 */
	void forwardPass(Node node) {
		stack.push(node);
		int Rf = Integer.parseInt(node.getAttr(rflag).toString());
		if(Rf>0 || isLeafNode(node)){
			//TODO
			backwardPass(node);
		}else {
			if(isBranchNode(node)) {
				Edge trueEdge = getTrueEdge(node);
				Node successorOne = trueEdge.to();
				node.putAttr(bflag, "1");
				multiplicities.put(successorOne, multiplicities.get(successorOne)+multiplicities.get(node));
				Rf = Integer.parseInt(successorOne.getAttr(rflag).toString());
				Rf = Rf - 1;
				successorOne.putAttr(rflag, Rf);
				forwardPass(successorOne);
				//TODO	
			} else {	
				//get successor node
				Node successor = dag.successors(Common.toQ(node)).eval().nodes().one();
				//propagate the multiplicity to successor
				multiplicities.put(successor, multiplicities.get(successor) + (multiplicities.get(node)));
				Rf = Integer.parseInt(successor.getAttr(rflag).toString());
				//decrease Rf by 1
				Rf = Rf-1;
				//update the Rf of successor
				successor.putAttr(rflag, Rf);
				//go forward
				//TODO
				forwardPass(successor);
			}
		}
		
	}
	
	/**
	 * @param node
	 * Follow lecture notes to know more about it
	 */
	void backwardPass(Node node) {
		int Rf = Integer.parseInt(node.getAttr(rflag).toString());
		if(Rf>0) {
		//TODO
			stack.pop();
			Node predecessor = stack.peek();
			backwardPass(predecessor);
		} else if(isBranchNode(node)) {
			if(node.getAttr(bflag).toString().equals("1")) {
				node.putAttr(bflag, "0");
				Edge falseEdge = getFalseEdge(node);
				multiplicities.put(falseEdge.to(), multiplicities.get(falseEdge.to())+multiplicities.get(node));
				int successorRf = Integer.parseInt(falseEdge.to().getAttr(rflag).toString());
				successorRf = successorRf-1;
				falseEdge.to().putAttr(rflag, Integer.toString(successorRf));
				Node successor = falseEdge.to();
				forwardPass(successor);
				}else {
					stack.pop();
					Node predecessor = stack.peek();
					backwardPass(predecessor);
				}
			//TODO			
		} else {
			//reached to root node
			if(isRootNode(node)){
				return;
			}
			//it's backward pass it pops the element from stack 
			stack.pop();
			// go backward
			Node predecessor = stack.peek();
			backwardPass(predecessor);
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
