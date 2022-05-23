package com.se421.paths.transforms;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Graph;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.db.set.AtlasHashSet;
import com.ensoftcorp.atlas.core.db.set.AtlasSet;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.query.Query;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.se421.paths.support.HelperQueries;

public class DAGTransform implements ProgramGraphTransform {

	public static final String REDIRECTED_CONTROL_FLOW_BACK_EDGE = "REDIRECTED_CONTROL_FLOW_BACK_EDGE";
	private Q dag;
	/**
	 * Transforms a CFG to an acyclic graph by redirecting control flow back edges
	 * to loop successors that are 1) not inside the loop body and 2) not inside
	 * trap regions (exception handlers).
	 * 
	 * @param cfg
	 * @return
	 */
	@Override
	public Q transform(Q cfg) {
		AtlasSet<Edge> backEdges = cfg.edges(XCSG.ControlFlowBackEdge).eval().edges();
		dag = cfg.differenceEdges(Common.toQ(backEdges));
		AtlasSet<Edge> redirectedBackEdges = new AtlasHashSet<Edge>();
		for(Edge backEdge : backEdges) {
			Node tail = backEdge.from();
			Node header = backEdge.to(); // also the exit
			AtlasSet<Node> candidateSuccessors = getCandidateSuccessors(cfg, tail, header);
			
			// for a do-while loop and goto's things can sometimes be reversed
			// note that the loop header may also be the loop exit and vice versa, so which
			// node we decide to label as the loop header is flexible
			if(candidateSuccessors.isEmpty()) {
				Node tmp = tail;
				tail = header;
				header = tmp;
				candidateSuccessors = getCandidateSuccessors(dag, tail, header);
				
				// for source its cleaner to display the actual loop header as the tail of the
				// redirected edge even if the "do" node is traditionally the loop header
				if(header.taggedWith(XCSG.DoWhileLoop)) {
					// restore the tail and header for display purposes so it looks like the
					// header is the tail and the successor is the head of the redirected edge
					// this is equivalent in terms of path counting
					tmp = tail;
					tail = header;
					header = tmp;
				}
			}
			
			// assert that there should always be exactly 1 candidate successor
			if(candidateSuccessors.isEmpty()) {
				throw new RuntimeException("No candidate successors could be found for [" + header.address().toAddressString() + "].");
			} else if(candidateSuccessors.size() > 1) {
				throw new RuntimeException("Header [" + header.address().toAddressString() + "] has multiple successors candidates.");
			}
			
			// add the redirected back edge
			Node successor = candidateSuccessors.one();
			Edge redirectedBackEdge = getOrCreateRedirectedBackEdge(tail, successor);
			redirectedBackEdges.add(redirectedBackEdge);
		}
		Q result = dag.union(Common.toQ(redirectedBackEdges));
		return Common.toQ(result.eval());
	}

	/**
	 * Get the candidate successors that are 1) not inside the loop body and 2) not inside
	 * trap regions (exception handlers).
	 * @param dag
	 * @param tail
	 * @param header
	 * @return
	 */
	private AtlasSet<Node> getCandidateSuccessors(Q cfg, Node tail, Node header){
		Q loopBodyPathToTail = dag.between(Common.toQ(header), Common.toQ(tail));
		AtlasSet<Node> succesorsOutsideLoop = new AtlasHashSet<Node>(cfg.differenceEdges(loopBodyPathToTail)
		.successors(Common.toQ(header)).eval().nodes());
		if(succesorsOutsideLoop.size()!=0) {
			if(cfg.betweenStep(Common.toQ(header),Common.toQ(succesorsOutsideLoop.getFirst())).edges(XCSG.ControlFlowBackEdge).eval().edges().size()>0) {
				succesorsOutsideLoop = getCandidateSuccessors(cfg,header,succesorsOutsideLoop.getFirst());
			}
		}
		Q catchBlockControlFlowNodes = HelperQueries.localDeclarations(Query.universe().nodes(XCSG.CatchBlock)).nodes(XCSG.ControlFlow_Node);
		AtlasSet<Node> candidateSuccessors = Common.toQ(succesorsOutsideLoop).difference(catchBlockControlFlowNodes).eval().nodes();
		return candidateSuccessors;
	}
	
	/**
	 * Creates an NPATH edge if it does not already exist between(predecessor,successor).
	 * 
	 * @param predecessor
	 * @param successor
	 * @return
	 */
	private Edge getOrCreateRedirectedBackEdge(Node predecessor, Node successor) {
		Q npathEdges = Query.universe().edges(REDIRECTED_CONTROL_FLOW_BACK_EDGE);
		Q betweenEdges = npathEdges.betweenStep(Common.toQ(predecessor), Common.toQ(successor));
		
		// first search to see if there is an existing edge
		Edge redirectedBackEdge = betweenEdges.eval().edges().one();
		
		// if the edge doesn't exist already then create it
		if(redirectedBackEdge == null) {
			redirectedBackEdge = Graph.U.createEdge(predecessor, successor);
			redirectedBackEdge.tag(REDIRECTED_CONTROL_FLOW_BACK_EDGE);
		}
		return redirectedBackEdge;
	}
	
}
