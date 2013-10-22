package com.sambuo.stackoverlook.utilities;

/**
 * Interface for a type-safe reduce function.
 * Code taken from http://stackoverflow.com/questions/223013/is-there-a-type-safe-java-implementation-of-reduce
 * 
 * @author Jimmy Sambuo
 *
 * @param <A>	The type output
 * @param <T>	The type input
 */
public interface Reducer<A, T> {
	A foldIn(A accum, T next);
}
