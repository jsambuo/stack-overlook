package com.sambuo.stackoverlook.utilities;

import java.util.ArrayList;

public class Utils {
	
	/**
	 * Converts an {@link Iterable} to an array
	 * 
	 * @param iterable	The {@link Iterable} to convert
	 * @return			The converted array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Iterable<T> iterable)
    {
		if (iterable == null) {
			return null;
		}
		
        return (T[]) Utils.toArrayList(iterable).toArray();
    }
	
	/**
	 * Converts an {@link Iterable} to an {@link ArrayList}
	 * 
	 * @param iterable	The {@link Iterable} to convert
	 * @return			The converted {@link ArrayList}
	 */
	public static <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
		
		if (iterable == null) {
			return null;
		}
		
		ArrayList<T> arrayList;
		if (iterable instanceof ArrayList<?>) {
			arrayList = (ArrayList<T>) iterable;
		}
		else {
			arrayList = new ArrayList<T>();
	        for(T item : iterable) {
	            arrayList.add(item);
	        }
		}
		
		return arrayList;
	}
}
