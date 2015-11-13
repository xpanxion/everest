package com.xpanxion.wallboard.rest.util;

import java.util.List;

public class ListUtils {

	private ListUtils() {
		
	}
	
	public static <T> boolean nullSafeAdd(List<T> list, T o) {
		if (null != o) {
			return list.add(o);
		}
		return false;
	}
}
