package com.se421.paths.support;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Graph;
import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.graph.GraphElement.EdgeDirection;
import com.ensoftcorp.atlas.core.db.graph.GraphElement.NodeDirection;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.db.graph.NodeGraph;
import com.ensoftcorp.atlas.core.db.graph.UncheckedGraph;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.db.set.EmptyAtlasSet;
import com.ensoftcorp.atlas.core.db.set.IntersectionSet;
import com.ensoftcorp.atlas.core.index.common.SourceCorrespondence;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.script.CommonQueries;
import com.ensoftcorp.atlas.core.script.CommonQueries.TraversalDirection;
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
	 * Returns the nodes whose names contain the given string.
	 * 
	 * Equivalent to nodesContaining(universe(), substring).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesContaining(String substring){
		return nodesMatchingRegex(Common.universe(), ".*" + Pattern.quote(substring) + ".*");
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
	 * Returns the nodes whose names end with the given string.
	 * 
	 * Equivalent to nodesEndingWith(universe(), suffix).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesEndingWith(String suffix){
		return nodesMatchingRegex(Common.universe(), ".*" + Pattern.quote(suffix));
	}
	
	/**
	 * Returns the nodes whose names end with the given string within the given
	 * context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesEndingWith(Q context, String suffix){
		return nodesMatchingRegex(context, ".*" + Pattern.quote(suffix));
	}
	
	/**
	 * Returns the nodes whose names match the given regular expression.
	 * 
	 * Equivalent to nodesMatchingRegex(universe(), regex).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesMatchingRegex(String regex){
		return nodesMatchingRegex(Common.universe(), regex);
	}
	
	/**
	 * Returns the nodes whose names start with the given string.
	 * 
	 * Equivalent to nodesStartingWith(universe(), prefix).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesStartingWith(String prefix){
		return nodesMatchingRegex(Common.universe(), Pattern.quote(prefix) + ".*");
	}
	
	/**
	 * Returns the nodes whose names start with the given string within the
	 * given context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesStartingWith(Q context, String prefix){
		return nodesMatchingRegex(context, Pattern.quote(prefix) + ".*");
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
	 * Returns the nodes whose attribute values contain the given string.
	 * 
	 * Equivalent to nodesAttributeValuesContaining(universe(), attribute, substring).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesContaining(String attribute, String substring){
		return nodesAttributeValuesMatchingRegex(Common.universe(), attribute, ".*" + Pattern.quote(substring) + ".*");
	}
	
	/**
	 * Returns the nodes whose attribute values contain the given string within the given
	 * context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesContaining(Q context, String attribute, String substring){
		return nodesAttributeValuesMatchingRegex(context, attribute, ".*" + Pattern.quote(substring) + ".*");
	}
	
	/**
	 * Returns the nodes whose attribute values end with the given string.
	 * 
	 * Equivalent to nodesAttributeValuesEndingWith(universe(), attribute, suffix).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesEndingWith(String attribute, String suffix){
		return nodesAttributeValuesMatchingRegex(Common.universe(), attribute, ".*" + Pattern.quote(suffix));
	}
	
	/**
	 * Returns the nodes whose attribute values end with the given string within the given
	 * context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesEndingWith(Q context, String attribute, String suffix){
		return nodesAttributeValuesMatchingRegex(context, attribute, ".*" + Pattern.quote(suffix));
	}
	
	/**
	 * Returns the nodes whose attribute values match the given regular expression.
	 * 
	 * Equivalent to nodesAttributeValuesMatchingRegex(universe(), attribute, regex).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesMatchingRegex(String attribute, String regex){
		return nodesAttributeValuesMatchingRegex(Common.universe(), attribute, regex);
	}
	
	/**
	 * Returns the nodes whose attribute values start with the given string.
	 * 
	 * Equivalent to nodesAttributeValuesStartingWith(universe(), attribute, prefix).
	 * 
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesStartingWith(String attribute, String prefix){
		return nodesAttributeValuesMatchingRegex(Common.universe(), attribute, Pattern.quote(prefix) + ".*");
	}
	
	/**
	 * Returns the nodes whose attribute values start with the given string within the
	 * given context.
	 * 
	 * @param context
	 * @param substring
	 * @return the query expression
	 */
	public static Q nodesAttributeValuesStartingWith(Q context, String attribute, String prefix){
		return nodesAttributeValuesMatchingRegex(context, attribute, Pattern.quote(prefix) + ".*");
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

	/**
	 * Returns the set of functions where their names matches the any of the
	 * names given (functionNames) list. A (*) in (functionNames) represents a
	 * wildcard that matches any string.
	 * 
	 * @param functionNames:
	 *            A list of function names as Strings
	 * @return A set of functions
	 */
	public static Q functions(String... functionNames){
		return find(XCSG.Function, functionNames);
	}

	/**
	 * Returns the nodes representing the global variable(s) given by the
	 * parameter list (names). A (*) in any string in the list (names)
	 * represents a wildcard that matches any string.
	 * 
	 * @param names:
	 *            A list of global variable names as Strings
	 * @return A set of global variable nodes
	 */
	public static Q globals(String... names){
		return find(XCSG.GlobalVariable, names);
	}

	/**
	 * Returns the nodes representing the types given by the names. 
	 * A (*) is a wildcard that matches any string.
	 * @param names A list of type names
	 * @return A set of global variable nodes
	 */
	public static Q types(String... names){
		return find(XCSG.Type, names);
	}
	
	/**
	 * Selects the Atlas graph element given a serialized graph
	 * element address
	 * 
	 * Returns null if the address does not correspond to a graph element
	 * 
	 * @param address
	 * @return
	 */
	public static GraphElement getGraphElementByAddress(String address){
		int hexAddress = Integer.parseInt(address, 16);
		GraphElement ge = Graph.U.getAt(hexAddress);
		return ge;
	}
	
	/**
	 * Selects the Atlas node graph element given a serialized graph
	 * element address
	 * 
	 * Returns null if the address does not correspond to a node
	 * 
	 * @param address
	 * @return
	 */
	public static Node getNodeByAddress(String address){
		GraphElement ge = getGraphElementByAddress(address);
		if(ge != null && ge instanceof Node){
			return (Node) ge;
		}
		return null;
	}
	
	/**
	 * Selects the Atlas edge graph element given a serialized graph
	 * element address
	 * 
	 * Returns null if the address does not correspond to a edge
	 * 
	 * @param address
	 * @return
	 */
	public static Edge getEdgeByAddress(String address){
		GraphElement ge = getGraphElementByAddress(address);
		if(ge != null && ge instanceof Edge){
			return (Edge) ge;
		}
		return null;
	}
	
	/**
	 * Returns the parameters of the given functions. 
	 * 
	 * Equivalent to functionParameter(universe(), functions)
	 * 
	 * @param functions
	 * @return the query expression
	 */
	public static Q functionParameter(Q functions){
		return functionParameter(Common.universe(), functions);
	}
	
	/**
	 * Returns the parameters of the given functions at the given indices. 
	 * 
	 * Equivalent to functionParameter(universe(), functions, index)
	 * 
	 * @param functions
	 * @param index
	 * @return the query expression
	 */
	public static Q functionParameter(Q functions, Integer... index){
		return functionParameter(Common.universe(), functions, index);
	}
	
	/**
	 * Returns the parameters of the given functions. Results are only returned if
	 * they are within the given context.
	 * 
	 * @param context
	 * @param functions
	 * @return the query expression
	 */
	public static Q functionParameter(Q context, Q functions){
		return functions.children().intersection(context).nodes(XCSG.Parameter);
	}
	
	/**
	 * Returns the parameters of the given functions at the given indices. Results
	 * are only returned if they are within the given context.
	 * 
	 * @param context
	 * @param functions
	 * @param index
	 * @return the query expression
	 */
	public static Q functionParameter(Q context, Q functions, Integer... index){
		return functionParameter(context, functions).selectNode(XCSG.parameterIndex, (Object[]) index);
	}
	
	/**
	 * Returns the return nodes for the given functions.
	 * 
	 * Equivalent to functionReturn(universe(), functions).
	 * 
	 * @param functions
	 * @return the query expression
	 */
	public static Q functionReturn(Q functions){
		return functionReturn(Common.universe(), functions);
	}
	
	/**
	 * Returns the return nodes for the given functions.
	 * @param context
	 * @param functions
	 * @return the query expression
	 */
	public static Q functionReturn(Q context, Q functions){
		return context.edgesTaggedWithAny(XCSG.Contains).successors(functions).nodes(XCSG.ReturnValue);
	}
	
	/**
	 * Returns the functions declared by the given types. 
	 * 
	 * Equivalent to functionsOf(universe(), types).
	 * 
	 * @param params
	 * @return the query expression
	 */
	public static Q functionsOf(Q types){
		return functionsOf(Common.universe(), types);
	}
	
	/**
	 * Returns the functions declared by the given types.
	 * 
	 * @param context
	 * @param types
	 * @return the query expression
	 */
	public static Q functionsOf(Q context, Q types){
		return types.nodes(XCSG.Type).children().intersection(context).nodes(XCSG.Function);
	}
	
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
	 * 
	 * @param functions
	 * @return the control flow graph under the function
	 */
	public static Q cfg(Q functions) {
		return com.ensoftcorp.atlas.core.script.CommonQueries.cfg(functions);
	}
	
	/**
	 * 
	 * @param function
	 * @return the control flow graph under the function
	 */
	public static Q cfg(Node function) {
		return cfg(Common.toQ(function));
	}
	
	/**
	 * 
	 * @param functions
	 * @return the control flow graph (including exceptional control flow) under the function
	 */
	public static Q excfg(Q functions) {
		return com.ensoftcorp.atlas.core.script.CommonQueries.excfg(functions);
	}
	
	/**
	 * 
	 * @param function
	 * @return the control flow graph (including exceptional control flow) under the function
	 */
	public static Q excfg(Node function) {
		return excfg(Common.toQ(function));
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
	 * Returns the direct callers of the given functions.
	 * 
	 * Operates in the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q callers(Q origin) {
		return callers(Common.universe(), origin);
	}

	/**
	 * Returns the direct callers of the given functions.
	 * 
	 * Operates in the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q callers(Q context, Q origin) {
		return context.edges(XCSG.Call).predecessors(origin);
	}

	/**
	 * Returns the subset of the given functions which are called.
	 * 
	 * Operates in the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q called(Q origin) {
		return called(Common.universe(), origin);
	}

	/**
	 * Returns the subset of the given functions which are called. Results are
	 * only returned if they are within the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q called(Q context, Q origin) {
		return context.edges(XCSG.Call).successors(origin);
	}

	/**
	 * Returns the given functions which were called by the given callers.
	 * 
	 * Operates in the index context.
	 * 
	 * @param callers
	 * @param called
	 * @return
	 */
	public static Q calledBy(Q callers, Q called) {
		return calledBy(Common.universe(), callers, called);
	}

	/**
	 * Returns the given functions which were called by the given callers. Results
	 * are only returned if they are within the given context.
	 * 
	 * @param context
	 * @param callers
	 * @param called
	 * @return
	 */
	public static Q calledBy(Q context, Q callers, Q called) {
		return context.edgesTaggedWithAny(XCSG.Call).betweenStep(callers, called).retainEdges().leaves();
	}

	/**
	 * Returns the first declaring node of the given Q which is tagged with one
	 * of the given types.
	 * 
	 * Operates in the index context.
	 * 
	 * @param declared
	 * @param declaratorTypes
	 * @return
	 */
	public static Q firstDeclarator(Q declared, String... declaratorTypes) {
		return firstDeclarator(Common.universe(), declared, declaratorTypes);
	}

	/**
	 * Returns the first declaring node of the given Q which is tagged with one
	 * of the given types. Results are only returned if they are within the
	 * given context.
	 * 
	 * @param context
	 * @param declared
	 * @param declaratorTypes
	 * @return
	 */
	public static Q firstDeclarator(Q context, Q declared, String... declaratorTypes) {
		Q subContext = declared.containers().intersection(context);
		subContext = subContext.differenceEdges(subContext.reverseStep(subContext.nodes(declaratorTypes)));
		return subContext.reverse(declared).nodes(declaratorTypes);
	}

	/**
	 * Given two query expressions, intersects the given node and edge kinds to
	 * produce a new expression.
	 * 
	 * @param first
	 * @param second
	 * @param nodeTags
	 * @param edgeTags
	 * @return
	 */
	public static Q advancedIntersection(Q first, Q second, String[] nodeTags, String[] edgeTags) {
		Q plainIntersection = first.intersection(second);
		return plainIntersection.nodes(nodeTags).induce(plainIntersection.edgesTaggedWithAny(edgeTags));
	}

	/**
	 * Returns the nodes which directly read from nodes in origin.
	 * 
	 * Operates in the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q readersOf(Q origin) {
		return readersOf(Common.universe(), origin);
	}

	/**
	 * Returns the nodes which directly read from nodes in origin.
	 * 
	 * Operates in the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q readersOf(Q context, Q origin) {
		return context.edgesTaggedWithAny(XCSG.DataFlow_Edge).successors(origin);
	}

	/**
	 * Returns the nodes which directly write to nodes in origin.
	 * 
	 * Operates in the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q writersOf(Q origin) {
		return writersOf(Common.universe(), origin);
	}

	/**
	 * Returns the nodes which directly write to nodes in origin.
	 * 
	 * Operates in the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q writersOf(Q context, Q origin) {
		return context.edgesTaggedWithAny(XCSG.DataFlow_Edge).predecessors(origin);
	}

	/**
	 * Returns the nodes from which nodes in the origin read.
	 * 
	 * Operates in the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q readBy(Q origin) {
		return readBy(Common.universe(), origin);
	}

	/**
	 * Returns the nodes from which nodes in the origin read.
	 * 
	 * Operates in the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q readBy(Q context, Q origin) {
		return writersOf(context, origin);
	}

	/**
	 * Returns the nodes to which nodes in origin write.
	 * 
	 * Operates in the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q writtenBy(Q origin) {
		return writtenBy(Common.universe(), origin);
	}

	/**
	 * Returns the nodes to which nodes in origin write.
	 * 
	 * Operates in the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q writtenBy(Q context, Q origin) {
		return readersOf(context, origin);
	}
	
	/**
	 * Returns the least common ancestor of both child1 and child2 within the given graph
	 * @param child1
	 * @param child2
	 * @param graph
	 * @return
	 */
	public static Node leastCommonAncestor(Node child1, Node child2, Graph graph){
		return leastCommonAncestor(child1, child2, Common.toQ(graph));
	}
	
	/**
	 * Returns the least common ancestor of both child1 and child2 within the given graph
	 * @param child1
	 * @param child2
	 * @param graph
	 * @return
	 */
	public static Node leastCommonAncestor(Node child1, Node child2, Q graph){
		Q ancestors = graph.reverse(Common.toQ(child1)).intersection(graph.reverse(Common.toQ(child2)));
		return ancestors.leaves().eval().nodes().one();
	}

	/**
	 * Returns the containing function of a given Q or empty if one is not found
	 * @param nodes
	 * @return
	 */
	public static Q getContainingFunctions(Q nodes) {
		AtlasSet<Node> nodeSet = new AtlasHashSet<Node>(nodes.eval().nodes());
		AtlasSet<Node> containingFunctions = new AtlasHashSet<Node>();
		for (Node currentNode : nodeSet) {
			Node function = getContainingFunction(currentNode);
			if (function != null){
				containingFunctions.add(function);
			}
		}
		return Common.toQ(Common.toGraph(containingFunctions));
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
			GraphElement containsEdge = Graph.U.edges(node, NodeDirection.IN).taggedWithAll(XCSG.Contains).one();
			if(containsEdge == null){
				return null;
			}
			Node parent = containsEdge.getNode(EdgeDirection.FROM);
			if(parent.taggedWith(containingTag)){
				return parent;
			}
			node = parent;
		}
	}
	
	/**
	 * Given a function, a branch, and an event of interest returns true if the
	 * branch governs whether or not the event of interest could be executed. If
	 * true the branch could prevent the event from being executed. The branch
	 * and event must both be contained in the same function. This method does 
	 * not consider exceptional control flow paths.
	 * 
	 * @param function
	 * @param branch
	 * @param event
	 * @return
	 */
	public static boolean isGoverningBranch(Node branch, Node event){
		return isGoverningBranch(branch, event, true);
	}
	
	/**
	 * Given a function, a branch, and an event of interest returns true if the
	 * branch governs whether or not the event of interest could be executed. If
	 * true the branch could prevent the event from being executed. The branch
	 * and event must both be contained in the same function.
	 * @param branch
	 *            An XCSG.ControlFlowCondition node
	 * @param event
	 *            An ControlFlow_Node node
	 * @param includeExceptionalPaths
	 *            If true considers exceptional control flow paths
	 * @return
	 */
	public static boolean isGoverningBranch(Node branch, Node event, boolean includeExceptionalPaths){
		Node branchFunction = getContainingFunction(branch);
		Node eventFunction = getContainingFunction(event);
		if(!branchFunction.equals(eventFunction)){
			throw new IllegalArgumentException("Branch and event must be contained in the same function.");
		}
		Node function = branchFunction;
		if(!branch.taggedWith(XCSG.ControlFlowCondition)){
			throw new IllegalArgumentException("branch parameter is not a control flow condition!");
		}
		if(!event.taggedWith(XCSG.ControlFlow_Node)){
			throw new IllegalArgumentException("event parameter is not a control flow node!");
		}
		Q cfg = includeExceptionalPaths ? excfg(function) : cfg(function);
		AtlasSet<Node> roots = cfg.roots().eval().nodes();
		if(roots.size() != 1){
			throw new RuntimeException("Function " + function.getAttr(XCSG.name) + " must only have one control flow root.");
		}
		Node root = roots.one();
		// a lovely rare corner case here, a void method can have a loop
		// with no termination conditions that forms a strongly connected
		// component, so root -> ... SCC, since the SCC will not have any
		// leaves could be empty. In order to deal with this we remove the
		// back edges to make the cfg leaves explicit
		AtlasSet<Node> exits = cfg.differenceEdges(cfg.edges(XCSG.ControlFlowBackEdge)).leaves().eval().nodes();
		if(exits.isEmpty()){
			throw new RuntimeException("Control flow graph does not have any exits.");
		}
		// is there a path from the root to the event that does not go through the branch?
		// if not then all paths must be going through the branch to reach the event and so the branch dominates the event
		boolean branchDominatesEvent = CommonQueries.isEmpty(cfg.between(Common.toQ(root), Common.toQ(event), Common.toQ(branch)));
		if(branchDominatesEvent){
			// is there a path that could be taken through at this branch where the event cannot occur?
			// i.e. this branch could potentially block the event from occurring
			boolean branchCanBlockEvent = !CommonQueries.isEmpty(cfg.between(Common.toQ(branch), Common.toQ(exits), Common.toQ(event)));
			// returns branchDominatesEvent && branchCanBlockEvent
			return branchCanBlockEvent;
		}
		return false;
	}

	/**
	 * Returns the control flow graph between conditional nodes and the given
	 * origin.
	 * 
	 * Operates within the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q conditionsAbove(Q origin) {
		return conditionsAbove(Common.universe(), origin);
	}

	/**
	 * Returns the control flow graph between conditional nodes and the given
	 * origin.
	 * 
	 * Operates within the given context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q conditionsAbove(Q context, Q origin) {
		Q conditionNodes = context.nodes(XCSG.ControlFlowCondition);
		return context.edgesTaggedWithAny(XCSG.ControlFlow_Edge).between(conditionNodes, origin);
	}

	/**
	 * Given a Q containing functions or data flow nodes, returns a Q of things
	 * which write to or call things in the Q.
	 * 
	 * Operates within the index context.
	 * 
	 * @param origin
	 * @return
	 */
	public static Q mutators(Q origin) {
		return mutators(Common.universe(), origin);
	}

	/**
	 * Returns those nodes in the context which have self edges.
	 * 
	 * @param context
	 * @return
	 */
	public static Q nodesWithSelfEdges(Q context) {
		AtlasSet<GraphElement> result = new AtlasHashSet<GraphElement>();
		for (Edge edge : context.eval().edges()) {
			Node to = edge.getNode(EdgeDirection.TO);
			Node from = edge.getNode(EdgeDirection.FROM);
			if (to == from){
				result.add(to);
			}
		}
		return Common.toQ(new NodeGraph(result));
	}

	/**
	 * Given a Q containing functions or data flow nodes, returns a Q of things
	 * which write to or call things in the Q.
	 * 
	 * Operates within the index context.
	 * 
	 * @param context
	 * @param origin
	 * @return
	 */
	public static Q mutators(Q context, Q origin) {
		return writersOf(context, origin).union(callers(context, origin));
	}

	/**
	 * Returns those elements in the origin which were called by or written by
	 * elements in the mutators set.
	 * 
	 * Operates within the index context.
	 * 
	 * @param mutators
	 * @param origin
	 * @return
	 */
	public static Q mutatedBy(Q mutators, Q origin) {
		return mutatedBy(Common.universe(), mutators, origin);
	}

	/**
	 * Returns those elements in the origin which were called by or written by
	 * elements in the mutators set.
	 * 
	 * Operates within the given context.
	 * 
	 * @param context
	 * @param mutators
	 * @param origin
	 * @return
	 */
	public static Q mutatedBy(Q context, Q mutators, Q origin) {
		return writtenBy(context, origin).union(calledBy(context, origin, mutators)).intersection(origin);
	}
	
	/**
	 * Helper function to get the stringified qualified name of the class
	 * @param type
	 * @return
	 */
	public static String getQualifiedTypeName(Node type) {
		if(type == null){
			throw new IllegalArgumentException("Type is null!");
		}
		if(!type.taggedWith(XCSG.Type)){
			throw new IllegalArgumentException("Type parameter is not a type!");
		}
		return getQualifiedName(type, XCSG.Package);
	}
	
	/**
	 * Helper function to get the stringified qualified name of the function
	 * @param function
	 * @return
	 */
	public static String getQualifiedFunctionName(Node function) {
		if(function == null){
			throw new IllegalArgumentException("Function is null!");
		}
		if(!function.taggedWith(XCSG.Function)){
			throw new IllegalArgumentException("Function parameter is not a function!");
		}
		return getQualifiedName(function, XCSG.Package);
	}
	
	/**
	 * Helper function to get the stringified qualified name of the function
	 * @param function
	 * @return
	 */
	public static String getQualifiedName(Node node) {
		return getQualifiedName(node, XCSG.Package);
	}
	
	/**
	 * Helper function to get the stringified qualified name of the class
	 * Stop after tags specify parent containers to stop qualifying at (example packages or jars)
	 * @param node
	 * @return
	 */
	public static String getQualifiedName(Node node, String...stopAfterTags) {
		if(node == null){
			throw new IllegalArgumentException("Node is null!");
		}
		String result = node.attr().get(XCSG.name).toString();
		Node parent = getDeclarativeParent(node);
		boolean qualified = false;
		while (parent != null && !qualified) {
			for(String stopAfterTag : stopAfterTags){
				if(parent.taggedWith(stopAfterTag)){
					qualified = true;
				}
			}
			String prefix = parent.attr().get(XCSG.name).toString();
			if(!prefix.equals("")){
				result = parent.attr().get(XCSG.name) + "." + result;
			}
			parent = getDeclarativeParent(parent);
		}
		return result;
	}
	
	/**
	 * Returns the single declarative parent
	 * Returns null if there is no parent
	 * Throws an IllegalArgumentException if there is more than one parent
	 * @param function
	 * @return
	 */
	private static Node getDeclarativeParent(Node node) {
		AtlasSet<Node> parentNodes = Common.toQ(node).parent().eval().nodes();
		if(parentNodes.size() > 1){
			throw new IllegalArgumentException("Multiple declarative parents!");
		}
		return parentNodes.one();
	}
	
	// begin utility functions
	
	private static Q find(String tag, String... names) {
		Q ts = Common.empty();
		for(String n : names){
			ts = ts.union(findByName(n, tag));
		}
		return ts;
	}
	
	/**
	 * Returns the nodes representing the structs having the given names. 
	 * <p>
	 * The name is automatically prefixed with "struct ".
	 * A (*) in name represents a wildcard that matches any string.
	 * <p>
	 * Examples:
	 * <ul>
	 * <li> structs("iblk")
	 * <li> structs("*bl*k*")
	 * <li> structs("pentry","bpool")
	 * </ul>
	 * 
	 * @param names list of struct names
	 * @return XCSG.C.Struct nodes
	 */
	public static Q structs(String... names){
		Q ts = Common.empty();
		for(String n : names){
			Q s = findByName("struct " + n, XCSG.C.Struct);
			ts = ts.union(s);
		}
		return ts;
	}
	
	/**
	 * Returns the induced call graph of functions referencing (read/write) the
	 * given global variables and/or types (structures) given in parameter
	 * (object).
	 * 
	 * @param object:
	 *            the set of global variables and/or types
	 * @return A set of type/structure/global variable nodes
	 */
	public static Q ref(Q object){
		Q refV = refVariable(object.nodes(XCSG.GlobalVariable));
		Q refT = refType(object.nodes(XCSG.C.Struct));
		return refV.union(refT);
	}
	
	/**
	 * Returns the control-flow graph (CFG) of the given function name in (funcName)
	 * @param funcName: function name as a String
	 * @return the CFG of the given function
	 */
	public static Q cfg(String funcName){
		return cfg(functions(funcName));
	}
	
	/**
	 * Returns the call graph of the given function(s) in (funcs)
	 * @param funcs: function node(s)
	 * @return The call graph of given function(s)
	 */
	public static Q cg(Q funcs){
		return edges(XCSG.Call).forward(funcs);
	}
	
	/**
	 * Returns the reverse-call graph of the given function(s) in (funcs)
	 * @param funcs: function node(s)
	 * @return The reverse call graph of given function(s)
	 */
	public static Q rcg(Q funcs){
		return edges(XCSG.Call).reverse(funcs);
	}
	
	/**
	 * Returns the set of function directly calling a given function(s)
	 * @param functs: function node(s)
	 * @return direct callers of given function(s)
	 */
	public static Q call(Q funcs){
		return edges(XCSG.Call).predecessors(funcs);
	}
	
	/**
	 * Returns the set of functions that are directly called by given function(s)
	 * @param functs: function node(s)
	 * @return direct callees by given function(s)
	 */
	public static Q calledBy(Q funcs){
		return edges(XCSG.Call).successors(funcs);
	}
	
	/**
	 * Returns the call graph between the functions in (roots) and functions in (leaves)
	 * @param roots: function node(s)
	 * @param leaves: function node(s)
	 * @return a call graph
	 */
	public static Q graph(Q roots, Q leaves){
		 return edges(XCSG.Call).between(roots, leaves);
	}
	
	/**
	 * Induces a call edges between the given set of function(s) in (funcs)
	 * @param functs: function node(s)
	 */
	public static Q induceCG(Q functs){
		return functs.induce(edges(XCSG.Call));
	}
	
	/**
	 * Induces the control-flow edges between the given control-flow blocks in (nodes)
	 * @param nodes: control-flow node(s)
	 */
	public static Q induceCFG(Q nodes){
		return nodes.induce(edges(XCSG.ControlFlow_Edge));
	}
	
	/**
	 * Returns the Matching Pair Graph for given object and event functions
	 * @param e1Functions: L function node(s)
	 * @param e2Functions: U function node(s)
	 * @param object: type node
	 * @return the matching pair graph for object (object)
	 */
	public static Q mpg(Q e1Functions, Q e2Functions, Q object){
		Q callL = call(e1Functions);
		Q callU = call(e2Functions);
		if(object.eval().nodes().one().taggedWith(XCSG.GlobalVariable)){
			callL = callL.intersection(refVariable(object));
			callU = callU.intersection(refVariable(object));
		}else if(object.eval().nodes().one().taggedWith(XCSG.C.Struct)){
			callL = callL.intersection(refType(object));
			callU = callU.intersection(refType(object));
		}
		Q rcg_lock = edges(XCSG.Call).reverse(callL);
		Q rcg_unlock = edges(XCSG.Call).reverse(callU);
		Q rcg_both = rcg_lock.intersection(rcg_unlock);
		Q rcg_c = rcg_lock.union(rcg_unlock);
		Q rcg_lock_only = rcg_lock.difference(rcg_both);
		Q rcg_unlock_only = rcg_unlock.difference(rcg_both);
		Q call_lock_only = callL.union(edges(XCSG.Call).reverseStep(rcg_lock_only));
		Q call_unlock_only = callU.union(edges(XCSG.Call).reverseStep(rcg_unlock_only));
		Q call_c_only = call_lock_only.union(call_unlock_only);
		Q balanced = call_c_only.intersection(rcg_both);
		Q ubc = balanced.union(rcg_lock_only, rcg_unlock_only);
		Q mpg = rcg_c.intersection(edges(XCSG.Call).forward(ubc));
		mpg = mpg.union(e1Functions, e2Functions);
		mpg = mpg.induce(edges(XCSG.Call));
		return mpg;
	}
	
	/**
	 * Returns the portion of data-flow graph within the given Function
	 * 
	 * @param dfg
	 *            A previously computed data-flow graph
	 * @param func
	 *            Function node(s)
	 * @return intra-procedural data-flow graph
	 */
	public static Q projectDFG(Q dfg, Q func){
		Q functionBody = func.contained().induce(Common.universe());
		return functionBody.intersection(dfg);
	}
	
	/**
	 * Returns the intra-procedural forward data-flow graph starting at the
	 * given node within the projection
	 * 
	 * @param projection
	 *            data-flow graph
	 * @param node
	 *            where the flow starts
	 * @return a forward data-flow graph from the selected node
	 */
	public static Q forwardProjection(Q projection, Q node){
		return projection.forward(node);
	}
	
	/**
	 * Returns the forward data-flow graph starting at the return value of
	 * Function "functionSource" in the context of Function "functionContext".
	 * The flow continues until it reaches a parameter of Function
	 * "functionSink".
	 * <p>
	 * In Xinu, try: <code>
	 * dfg("dsread", "getbuf", "freebuf")
	 * </code>
	 * 
	 * @param functionContext
	 *            caller of functionSource
	 * @param functionSource
	 *            return value to use as origin in data-flow graph
	 * @param functionSink
	 *            stopping point for data-flow graph
	 */
	public static Q dfg(String functionContext, String functionSource, String functionSink) {
		// find CallSites to "functionSource" within "functionContext"
		Q qFunctionSource = functions(functionSource);
		Q callSites = edges(XCSG.InvokedFunction).predecessors(qFunctionSource);
		Q callSitesInFunction = functions(functionContext).contained().intersection(callSites);

		// take the contents of "functionSink" as the stopping point for flows
		Q functionSinkParameters = functions(functionSink).contained();
		
		AtlasSet<Node> origin = callSitesInFunction.eval().nodes();
		AtlasSet<Node> stop = functionSinkParameters.eval().nodes();
		
		Graph forwardDF = Traversal.forwardDF(origin, stop);
		
		return Common.toQ(forwardDF);
	}
	
	/**
	 * Returns Types, GlobalVariables, Functions, and Macros
	 * with source correspondence matching the given path.
	 * 
	 * Wildcards are allowed.
	 * Paths are always normalized to use / as a directory separator.
	 * Paths are always assumed to start with a wildcard (to avoid needing to 
	 * include the full project path).
	 * <p>
	 * Examples:
	 * <ul>
	 * <li> file("open.c")  -- any file ending with open.c
	 * <li> file("/open.c")  -- exactly files named open.c
	 * <li> file("/dg*.c")  -- any file starting with dg and ending with .c
	 * </ul>
	 * 
	 * @param path 
	 * @return
	 */
	public static Q file(String path) {
		
		Q entities = Common.codemap().nodes(
				XCSG.Type,
				XCSG.TypeAlias,
				XCSG.GlobalVariable,
				XCSG.Function,
				XCSG.C.Provisional.Macro);
		
		String regex = ".*" + path.replace("*", ".*");
		Pattern pattern = Pattern.compile(regex);
		
		AtlasSet<Node> matches = new AtlasHashSet<>();
		for (Node entity : entities.eval().nodes()) {
			if (scMatches(entity,pattern)) matches.add(entity);
		}
		
		return Common.toQ(matches);
	}

	/******************************************************************/
	/******************************************************************/
	/*                   Utility Functions Below                      */ 
	/******************************************************************/
	/******************************************************************/

	private static Q findByName(String functionName, String tag) {
		if(functionName.indexOf("*") >= 0){
			Q nodes = Common.universe().nodes(tag);
			Q result = getMatches(functionName, nodes);
			return result;
		}
		// Atlas has an index over literal attribute values, so it's faster to query directly
		return Common.universe().nodes(tag).selectNode(XCSG.name, functionName);
	}

	/**
	 * tests source correspondence path
	 * @param n
	 * @param pattern
	 * @return true if sourceCorrespondence file path matches
	 */
	private static boolean scMatches(Node n, Pattern pattern) {
		SourceCorrespondence sc = (SourceCorrespondence) n.getAttr(XCSG.sourceCorrespondence);
		if (sc == null) return false;
		
		IPath fullPath = sc.sourceFile.getFullPath();
		String path = fullPath.toOSString();
	
		Matcher matcher = pattern.matcher(path);
		return matcher.matches();
	}

	/**
	 * Returns a filter over the edges which describe the type
	 * of a Variable or DataFlow_Node.
	 * @return
	 */
	public static Q typeEdges() {
		Q typeEdges = edges(XCSG.TypeOf, // from Variable or DataFlow_Node to a Type
				XCSG.ArrayElementType, // arrays have an ArrayElementType
				XCSG.ReferencedType, // pointers have a ReferencedType
				XCSG.AliasedType,  // typedefs have an AliasedType
				XCSG.C.CompletedBy // OpaqueTypes are connected to the corresponding Type 
				);
		return typeEdges;
	}
	
	/**
	 * The induced call graph on the Functions which contain Variable or DataFlow_Nodes 
	 * having the given type.
	 * 
	 *  
	 * @param type
	 * @return
	 */
	private static Q refType(Q type){
		Q ref = typeEdges().reverse(type);
		ref = Common.extend(ref, XCSG.Contains);
		return ref.nodes(XCSG.Function).induce(edges(XCSG.Call));
	}
	
	/**
	 * The induced call graph on the functions which read or write the given variables.
	 * 
	 * @param variable
	 * @return the induced Call Graph
	 */
	private static Q refVariable(Q variable){
		Q read =  edges(XCSG.Reads).reverseStep(variable);
		Q write = edges(XCSG.Writes).reverseStep(variable);
		Q all = read.union(write);
		all = Common.extend(all, XCSG.Contains);
		return all.nodes(XCSG.Function).induce(edges(XCSG.Call));
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
	 * Returns all nodes with a name matching the wildcard expression
	 * 
	 * @param name
	 * @param nodes
	 * @return
	 */
	private static Q getMatches(String name, Q nodes){
		name = name.replace("*", ".*");
		AtlasSet<Node> allNodes = nodes.eval().nodes();
		AtlasSet<GraphElement> result = new AtlasHashSet<GraphElement>();
		
		for(GraphElement node : allNodes){
			String thisName = (String) node.getAttr(XCSG.name);
			if(thisName.matches(name)){
				result.add(node);
			}
		}
		return Common.toQ(Common.toGraph(result));
	}
	
	/**
	 * Extends the given graph through the edges describing the type of the nodes.
	 * @see CommonQueries#typeEdges()
	 * @param q
	 * @return The given graph extended along the type edges.
	 */
	public static Q typeOf(Q q) {
		return q.forwardOn(typeEdges());
	}
	
	/**
	 * Selects all functions, and one step of data flow for
	 * the capture of the address of a function.
	 * 
	 * @return
	 */
	public static Q functionPtr(){
		Q dataFlowEdges = edges(XCSG.DataFlow_Edge);
		Q functions = Common.codemap().nodes(XCSG.Function);
		return dataFlowEdges.forwardStep(functions);
	}
	
	/**
	 * Filter for edges in the code map
	 * @param tags
	 * @return
	 */
	private static Q edges(String ... tags) {
		return Common.codemap().edges(tags);
	}
	
	@SuppressWarnings("unused")
	private static class Traversal {
		
		public static Graph reverseDF(AtlasSet<Node> origin) {
			AtlasSet<Node> stop = EmptyAtlasSet.<Node>instance(Graph.U);
			return reverseDF(origin, stop);
		}
		
		public static Graph reverseDF(AtlasSet<Node> origin, AtlasSet<Node> stop) {
			Graph context = Common.universe().edgesTaggedWithAny(XCSG.DataFlow_Edge).eval();
			return traverse(context, TraversalDirection.REVERSE, origin, stop);
		}
		
		public static Graph forwardDF(AtlasSet<Node> origin) {
			AtlasSet<Node> stop = EmptyAtlasSet.<Node>instance(Graph.U);
			return forwardDF(origin, stop);
		}
		
		public static Graph forwardDF(AtlasSet<Node> origin, AtlasSet<Node> stop) {
			Graph context = Common.universe().edgesTaggedWithAny(XCSG.DataFlow_Edge).eval();
			return traverse(context, TraversalDirection.FORWARD, origin, stop);
		}

		/**
		 * Selects a subgraph of the given graph, by traversal in a given direction
		 * from the origin nodes.
		 * 
		 * @param graph
		 * @param direction FORWARD or REVERSE
		 * @param origin possible starting nodes
		 * @param stop nodes at which to stop traversal if encountered; such nodes are included in the result
		 * @return
		 */
		public static Graph traverse(Graph graph, TraversalDirection direction, AtlasSet<Node> origin, AtlasSet<Node> stop) {
			
			final NodeDirection nodeDirection = (direction == TraversalDirection.REVERSE) ? NodeDirection.IN : NodeDirection.OUT;
			final EdgeDirection edgeDirection = (direction == TraversalDirection.REVERSE) ? EdgeDirection.FROM : EdgeDirection.TO;
			
			AtlasHashSet<Node> nodesInGraph = new AtlasHashSet<>();
			AtlasHashSet<Edge> edgesInGraph = new AtlasHashSet<>();

			AtlasHashSet<Node> frontier = new AtlasHashSet<>();
			frontier.addAll(new IntersectionSet<Node>(graph.nodes(), origin));
			
			while (!frontier.isEmpty()) {
				Iterator<Node> itr = frontier.iterator();
				Node currentNode = itr.next();
				itr.remove();

				nodesInGraph.add(currentNode);
				
				if (stop.contains(currentNode)){
					continue;
				}
				
				AtlasSet<Edge> edges = graph.edges(currentNode, nodeDirection);

				for (Edge edge : edges) {
					edgesInGraph.add(edge);
					Node nextNode = edge.getNode(edgeDirection);
					if (!nodesInGraph.contains(nextNode)) {
						frontier.add(nextNode);
					}
				}
				
			}
			
			return new UncheckedGraph(nodesInGraph, edgesInGraph);
		}
	}
	
}