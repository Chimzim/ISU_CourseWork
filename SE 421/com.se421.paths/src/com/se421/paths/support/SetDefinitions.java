package com.se421.paths.support;

import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.script.Common;
import com.ensoftcorp.atlas.core.xcsg.XCSG;

/**
 * Common set definitions which are useful for program analysis
 * 
 * @author Tom Deering, Ben Holland
 */
public final class SetDefinitions {

	// hide constructor
	private SetDefinitions() {}

	/**
	 * Types which represent arrays of other types
	 * 
	 * NOTE: These nodes are NOT declared by anything. They are outside of any
	 * project.
	 */
	public static Q arrayTypes() {
		return Common.universe().nodesTaggedWithAny(XCSG.ArrayType);
	}

	/**
	 * Types which represent language primitive types
	 * 
	 * NOTE: These nodes are NOT declared by anything. They are outside of any
	 * project.
	 */
	public static Q primitiveTypes() {
		return Common.universe().nodesTaggedWithAny(XCSG.Primitive);
	}

	/**
	 * Everything declared under any of the known API projects, if they are in
	 * the index.
	 */
	public static Q libraries() {
		return Common.universe().nodesTaggedWithAny(XCSG.Library).contained();
	}
	
	/**
	 * Everything in the universe which is part of the app (not part of the
	 * libraries, or any "floating" nodes).
	 */
	public static Q app() {
		return Common.universe().difference(libraries(), primitiveTypes(), arrayTypes());
	}

}
