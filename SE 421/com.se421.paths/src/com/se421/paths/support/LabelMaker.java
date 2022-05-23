package com.se421.paths.support;

import java.util.Map;
import java.util.Map.Entry;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.markup.Markup;
import com.ensoftcorp.atlas.core.markup.MarkupProperty;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;

public class LabelMaker {

	/**
	 * Applies a set of node labels
	 * @param markup
	 * @param labels
	 * @return
	 */
	public static Markup setNodeLabels(Markup markup, Map<Node,String> labels){
		for(Entry<Node,String> entry : labels.entrySet()){
			markup.setNode(Common.toQ(entry.getKey()), MarkupProperty.LABEL_TEXT, entry.getValue());
		}
		return markup;
	}
	
	/**
	 * Applies a set of edge labels
	 * @param markup
	 * @param labels
	 * @return
	 */
	public static Markup setEdgeLabels(Markup markup, Map<Edge,String> labels){
		for(Entry<Edge,String> entry : labels.entrySet()){
			markup.setEdge(Common.toQ(entry.getKey()), MarkupProperty.LABEL_TEXT, entry.getValue());
		}
		return markup;
	}
	
	/**
	 * Applies a set of node prefix labels
	 * @param markup
	 * @param labels
	 * @return
	 */
	public static Markup setNodePrefixLabels(Markup markup, Map<Node,String> labels){
		for(Entry<Node,String> entry : labels.entrySet()){
			String name = entry.getKey().getAttr(XCSG.name).toString();
			markup.setNode(Common.toQ(entry.getKey()), MarkupProperty.LABEL_TEXT, entry.getValue() + name);
		}
		return markup;
	}
	
	/**
	 * Applies a set of edge prefix labels
	 * @param markup
	 * @param labels
	 * @return
	 */
	public static Markup setEdgePrefixLabels(Markup markup, Map<Edge,String> labels){
		for(Entry<Edge,String> entry : labels.entrySet()){
			String name = entry.getKey().getAttr(XCSG.name).toString();
			markup.setEdge(Common.toQ(entry.getKey()), MarkupProperty.LABEL_TEXT, entry.getValue() + name);
		}
		return markup;
	}
	
	/**
	 * Applies a set of node suffix labels
	 * @param markup
	 * @param labels
	 * @return
	 */
	public static Markup setNodeSuffixLabels(Markup markup, Map<Node,String> labels){
		for(Entry<Node,String> entry : labels.entrySet()){
			String name = entry.getKey().getAttr(XCSG.name).toString();
			markup.setNode(Common.toQ(entry.getKey()), MarkupProperty.LABEL_TEXT, name + entry.getValue());
		}
		return markup;
	}
	
	/**
	 * Applies a set of edge suffix labels
	 * @param markup
	 * @param labels
	 * @return
	 */
	public static Markup setEdgeSuffixLabels(Markup markup, Map<Edge,String> labels){
		for(Entry<Edge,String> entry : labels.entrySet()){
			String name = entry.getKey().getAttr(XCSG.name).toString();
			markup.setEdge(Common.toQ(entry.getKey()), MarkupProperty.LABEL_TEXT, name + entry.getValue());
		}
		return markup;
	}
	
}