package com.se421.paths.support;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Graph;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.index.common.SourceCorrespondence;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;

/**
 * This is an illustrative example of how to list line numbers corresponding to
 * nodes.
 * Note that the startLine field SourceCorrespondence field is only
 * valid for C projects. In Java projects the startLine field will be
 * initialized to -1 because source correspondence is stored as a file offset.
 * 
 * Example Usage:
 * com.se421.paths.support.CFGPrinter.printCFG(cfg(functions("dskqopt")), "C:\\Users\\TA\\Desktop\\dskqopt_cfg.txt")
 * 
 * @author Payas Awadhutkar
 * @author Ben Holland
 */
public class CFGPrinter {
	
	private static final boolean LINE_NUMBERS_ONLY = false;
	private static final boolean USE_NODE_NAMES = false; // noisy, use for debugging
	
	/**
	 * For every node in the CFG print the nodes control flow successors
	 * Print references to nodes by printing the corresponding starting line number and CF node type
	 */
	public static void printCFG(Q cfg, String filePath) throws IOException {
		// input sanitization to assert the cfg is actually a cfg (filter out any non CF nodes/edges)
		cfg = cfg.nodes(XCSG.ControlFlow_Node).induce(cfg.edges(XCSG.ControlFlow_Edge));

		// for each node in the cfg
		Graph cfgGraph = cfg.eval();
		
		// sort nodes by line number
		List<Node> sortedCFGNodes = new ArrayList<Node>();
		for(Node cfgNode : cfgGraph.nodes()) {
			sortedCFGNodes.add(cfgNode);
		}
		Collections.sort(sortedCFGNodes, new Comparator<Node>() {
			@Override
			public int compare(Node n1, Node n2) {
				SourceCorrespondence n1SC = (SourceCorrespondence) n1.getAttr(XCSG.sourceCorrespondence);
				int n1StartLine = -1;
				if(n1SC != null) {
					n1StartLine = n1SC.startLine;
				}
				int n2StartLine = -1;
				SourceCorrespondence n2SC = (SourceCorrespondence) n2.getAttr(XCSG.sourceCorrespondence);
				if(n2SC != null) {
					n2StartLine = n2SC.startLine;
				}
				return Integer.compare(n1StartLine, n2StartLine);
			}
		});
		
		// create a file writer to serialize results
		FileWriter writer = new FileWriter(filePath);
		writer.write("CFG Node:Successors\n");
		
		// iterate over each cfg node in the sorted list of all cfg nodes
		for(Node cfgNode : sortedCFGNodes) {
			SourceCorrespondence sc = (SourceCorrespondence) cfgNode.getAttr(XCSG.sourceCorrespondence);
			String nodeDescription = getCFGNodeDescription(cfgNode, cfgGraph);
			nodeDescription += " (" + sc.startLine + ")";
			String successorDescriptions = "";
			String prefix = "";
			Node predecessor = cfgNode;
			for(Edge cfgEdge : cfg.forwardStep(Common.toQ(predecessor)).eval().edges()) {
				Node successor = cfgEdge.to();
				SourceCorrespondence successorSC = (SourceCorrespondence) successor.getAttr(XCSG.sourceCorrespondence);
				String successorDescription = getCFGNodeDescription(successor, cfgGraph);
				if(!LINE_NUMBERS_ONLY) {
					if(cfgEdge.hasAttr(XCSG.conditionValue)){
						successorDescription = (" conditional-" + cfgEdge.getAttr(XCSG.conditionValue).toString() + "-" + successorDescription);
					} else {
						successorDescription = (" unconditional-" + successorDescription);
					}
				}
				successorDescription += " (" + successorSC.startLine + ")";
				successorDescriptions += (prefix + successorDescription);
				if(prefix.isEmpty()) {
					prefix = ",";
				}
			}
			if(LINE_NUMBERS_ONLY) {
				String line = nodeDescription.trim() + ":[" + successorDescriptions + "]\n";
				writer.write(line.replace("(", "").replace(")", "").replace(" ", ""));
			} else {
				writer.write(nodeDescription.trim() + ":[" + successorDescriptions + "]\n");
			}
			writer.flush();
		}
		writer.close();
	}
	
	/**
	 * Helper method to print a description of the node
	 * @param cfgNode
	 * @param cfg
	 * @return
	 */
	private static String getCFGNodeDescription(Node cfgNode, Graph cfg) {
		if(LINE_NUMBERS_ONLY) {
			return "";
		} else if(USE_NODE_NAMES) {
			return cfgNode.getAttr(XCSG.name).toString();
		} else {
			String description = "";
			if(cfg.roots().contains(cfgNode)) {
				description += "root-";
			}
			if(cfg.leaves().contains(cfgNode)) {
				description += "leaf-";
			}
			if(cfgNode.taggedWith(XCSG.ControlFlowCondition)) {
				// includes loop and branch and switch conditions
				if(cfgNode.taggedWith(XCSG.ControlFlowLoopCondition)) {
					description += "loop";
				}
				if(cfgNode.taggedWith(XCSG.ControlFlowIfCondition)) {
					description += "branch";
				}
				if(cfgNode.taggedWith(XCSG.ControlFlowSwitchCondition)) {
					description += "switch";
				}
			} else {
				if(cfgNode.taggedWith(XCSG.controlFlowExitPoint)) {
					description += "exit"; // a non-branching control flow node
				} else {
					description += "statement"; // a boring non-branching control flow node
				}
			}
			return description;
		}
	}
}