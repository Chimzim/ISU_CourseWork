package com.se421.dataflow.support;

import java.util.Iterator;
import java.util.regex.Pattern;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Graph;
import com.ensoftcorp.atlas.core.db.graph.GraphElement.NodeDirection;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.db.graph.NodeGraph;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;

/**
 * Common queries which are useful for writing larger language agnostic analysis
 * programs, and for using on the shell. This also acts as a wrapper around
 * relevant <code>com.ensoftcorp.atlas.core.script.CommonQueries</code> API functions.
 * 
 * @author Ben Holland, Tom Deering, Jon Mathews
 */
public final class HelperQueries {	
	
	// hide constructor
	private HelperQueries() {}
	
	/**
	 * 
	 * @param functions
	 * @return the data flow graph under the function
	 */
	public static Q dfg(Q functions) {
		return localDeclarations(functions).nodes(XCSG.DataFlow_Node).induce(Common.edges(XCSG.DataFlow_Edge));
	}
	
	/**
	 * 
	 * @param function
	 * @return the data flow graph under the function
	 */
	public static Q dfg(Node function) {
		return dfg(Common.toQ(function));
	}
	
	/**
	 * Returns whether the given Q is empty.
	 * 
	 * @param test
	 * @return
	 */
	public static boolean isEmpty(Q test){
		return !isNotEmpty(test);
	}
	
	/**
	 * Returns whether the given Q is not empty
	 * @param test
	 * @return
	 */
	public static boolean isNotEmpty(Q test) {
		return test.eval().nodes().iterator().hasNext();
	}
	
	/**
	 * All nodes declared under the given functions, but NOT declared under
	 * additional functions or types. Retrieves declarations of only this function.
	 * Results are only returned if they are within the given context.
	 * 
	 * @param functions
	 * @return
	 */
	public static Q localDeclarations(Q functions) {
		return localDeclarations(Common.universe(), functions);
	}

	/**
	 * All nodes declared under the given functions, but NOT declared under
	 * additional functions or types. Retrieves declarations of only this function.
	 * Results are only returned if they are within the given context.
	 * 
	 * @param context
	 * @param functions
	 * @return
	 */
	public static Q localDeclarations(Q context, Q functions) {
		AtlasSet<Node> result = new AtlasHashSet<Node>();
		AtlasSet<Node> worklist = new AtlasHashSet<Node>(functions.children().eval().nodes());
		while(!worklist.isEmpty()){
			Iterator<Node> iter = worklist.iterator();
			Node child = iter.next();
			iter.remove();
			if(child.taggedWith(XCSG.Type) || child.taggedWith(XCSG.Function)){
				continue;
			} else {
				result.add(child);
				worklist.addAll(Common.toQ(child).children().eval().nodes());
			}
		}
		return Common.toQ(result);
	}
	
	/**
	 * Returns nearest parents which are a control flow node
	 * @param node
	 * @return
	 */
	public static Q getContainingControlFlowNode(Q nodes) {
		AtlasSet<Node> nodeSet = new AtlasHashSet<Node>(nodes.eval().nodes());
		AtlasSet<Node> containingControlFlowNodes = new AtlasHashSet<Node>();
		for(Node currentNode: nodeSet) {
			Node controlFlowNode = getContainingControlFlowNode(currentNode);
			if(controlFlowNode != null) {
				containingControlFlowNodes.add(controlFlowNode);
			}
		}
		return Common.toQ(Common.toGraph(containingControlFlowNodes));
	}
	
	/**
	 * Returns the nearest parent that is a control flow node
	 * @param node
	 * @return
	 */
	public static Node getContainingControlFlowNode(Node node) {
		// NOTE: this logic considers that the enclosing control flow node may be two steps or more above
		return getContainingNode(node, XCSG.ControlFlow_Node);
	}

	/**
	 * Returns the containing function of a given Q or empty if one is not found
	 * @param nodes
	 * @return
	 */
	public static Q getContainingFunctions(Q nodes) {
		AtlasSet<Node> nodeSet = new AtlasHashSet<Node>(nodes.eval().nodes());
		AtlasSet<Node> containingFunctions = new AtlasHashSet<Node>();
		for(Node currentNode : nodeSet) {
			Node function = getContainingFunction(currentNode);
			if (function != null) {
				containingFunctions.add(function);
			}
		}
		return Common.toQ(Common.toGraph(containingFunctions));
	}

	/**
	 * Returns the containing function of a given graph element or null if one is not found
	 * @param node
	 * @return
	 */
	public static Node getContainingFunction(Node node) {
		// NOTE: this logic considers that the enclosing function may be two steps or more above
		return getContainingNode(node, XCSG.Function);
	}
	
	/**
	 * Find the next immediate containing node with the given tag.
	 * 
	 * @param node 
	 * @param containingTag
	 * @return the next immediate containing node, or null if none exists; never returns the given node
	 */
	public static Node getContainingNode(Node node, String containingTag) {
		if(node == null){
			return null;
		}
		while(true) {
			Edge containsEdge = Graph.U.edges(node, NodeDirection.IN).tagged(XCSG.Contains).one();
			if(containsEdge == null){
				return null;
			}
			Node parent = containsEdge.from();
			if(parent.taggedWith(containingTag)){
				return parent;
			}
			node = parent;
		}
	}
	
	/**
	 * Returns the Functions containing the given nodes (if any).
	 * Not all nodes are nested under a Function.
	 * 
	 * @param nodes
	 * @return Functions containing the given nodes
	 */
	public static Q getContainingFunction(Q nodes) {
		AtlasSet<Node> ns = nodes.eval().nodes();
		AtlasSet<Node> containingFunctions = new AtlasHashSet<>();
		for (Node node : ns) {
			Node function = getContainingFunction(node);
			if (function != null)
				containingFunctions.add(function);
		}
		Q functions = Common.toQ(Common.toGraph(containingFunctions));
		
		return functions;
	}
	
	/**
	 * Returns the nodes whose names contain the given string within the given
	 * context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesContaining(Q context, String substring){
		return nodesMatchingRegex(context, ".*" + Pattern.quote(substring) + ".*");
	}
	
	/**
	 * Returns the nodes whose names match the given regular expression within
	 * the given context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesMatchingRegex(Q context, String regex){
		return nodesAttributeValuesMatchingRegex(context, XCSG.name, regex);
	}
	
	/**
	 * Returns the nodes whose attribute values match the given regular
	 * expression within the given context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesMatchingRegex(Q context, String attribute, String regex){
		AtlasSet<Node> result = new AtlasHashSet<Node>();
		Iterator<Node> iterator = context.eval().nodes().iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			Object value = node.getAttr(attribute);
			if(value != null){
				if(value instanceof String){
					String name = (String) value;
					if (name.matches(regex)) {
						result.add(node);
					}
				}
			}
		}
		return Common.toQ(new NodeGraph(result));
	}
}