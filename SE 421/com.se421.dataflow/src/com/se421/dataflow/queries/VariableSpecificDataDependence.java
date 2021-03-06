package com.se421.dataflow.queries;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.se421.dataflow.dependence.DataDependenceGraph;
import com.se421.dataflow.support.HelperQueries;
import com.ensoftcorp.atlas.core.xcsg.XCSG;

/**
 * This program computes all UD chains at a given program point for the specified variable.
 * 
 * @author Chimzim Ogbondah
 */

public class VariableSpecificDataDependence {

	public static Q variableSpecificDataDependence(Q selectedStatement, String variableName) {
		/* 
		 * TODO: 1) Ensure that your selection corresponds to a control flow statement.
		 * If not handled properly, your may end up selecting a variable within the statement.
		 * You can either refer to the solution discussed in lecture notes
		 * or invoke an appropriate API provided in HelperQueries
		 */
		Q selectedControlFlow = Common.empty();
	    selectedControlFlow = HelperQueries.getContainingControlFlowNode(selectedStatement); //ensures that the selected node is a control flow node
		
		// DO NOT MODIFY this section
		Q containingFunction = HelperQueries.getContainingFunction(selectedControlFlow);
		Q dfg = HelperQueries.dfg(containingFunction);
		DataDependenceGraph ddg = new DataDependenceGraph(dfg.eval());
		Q ddgQ = ddg.getGraph();
		Q dataDependenceEdges = ddgQ.edges("data-dependence");                                            
		
		// An empty graph to store the UD chains
		Q variableSpecificDataDependenceGraph = Common.empty();
		
		/*
		 * TODO: 2) You need to get the variable-specific edges using variableName
		 * You will need to use the attribute "dependent-variable"
		 */
		Q variableDependenceEdges = dataDependenceEdges.edges("dependent-variable", variableName); //Gets all edges with tag dependent variable based on the variable name 
		//from the data dependence graph
		
		/*
		 * TODO: 3) You need to use these edges to get the variable specific UD chains
		 * Store them in the variable variableSpecificDataDependenceGraph
		 * You may need to create intermediate variables here.
		 */
		variableSpecificDataDependenceGraph = (dataDependenceEdges.union(variableDependenceEdges)).reverse(selectedControlFlow); //gets the union of the data dependence edges 
		//and the variable dependences edges and then reverses them based on the control flow so we get the effective UD chains
		
		return variableSpecificDataDependenceGraph;
	}

}
