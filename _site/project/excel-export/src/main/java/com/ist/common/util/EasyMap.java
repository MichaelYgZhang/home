package com.ist.common.util;

import java.util.Collections;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明: hibernate风格的map<br>
 * 
 * @author oofrank<br>
 * @param <V>
 */
public class EasyMap<K, V> extends HashMap<K, V> {
	public static final Map<Object, Object> EMPTY_MAP = Collections
			.unmodifiableMap(new EasyMap<Object, Object>());

	private static final long serialVersionUID = -5263619899963964073L;

	public EasyMap<K, V> putValue(K key, V value) {
		super.put(key, value);
		return this;
	}

	@Override
	public V put(K key, V value) {
		throw new RuntimeException("请不要使用此方法！");
	}

}
