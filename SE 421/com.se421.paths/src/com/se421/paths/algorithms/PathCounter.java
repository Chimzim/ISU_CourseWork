package com.se421.paths.algorithms;

import com.ensoftcorp.atlas.core.query.Q;

/**
 * A common interface of program path counting algorithms
 *
 * @author Sharwan
 */
public abstract class PathCounter {

	/**
	 * Counts the number of paths in a given control flow graph
	 * @param cfg
	 * @return
	 */
	public abstract CountingResult countPaths(Q cfg);

	/**
	 * Holds a path counting result, which consists of the number of paths counted
	 */
	public static class CountingResult {
		private long paths = 0L;

		public CountingResult(long paths) {
			this.paths = paths;
		}

		public long getPathCount() {
			return paths;
		}

		@Override
		public String toString() {
			return "Result [paths=" + paths + "]";
		}
	}

}
