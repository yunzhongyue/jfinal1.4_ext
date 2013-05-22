/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon.components.guavacache;

import java.util.Collection;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jdon.container.pico.Startable;

public class GuavaCacheProvider implements com.jdon.controller.cache.Cache, Startable {

	private Cache<Object, Object> cache;

	private final GuavaCacheConf guavaCacheConf;

	public GuavaCacheProvider(GuavaCacheConf guavaCacheConf) {
		this.guavaCacheConf = guavaCacheConf;
	}

	@Override
	public void start() {
		cache = CacheBuilder.newBuilder().maximumSize(guavaCacheConf.getMaximumSize()).build();
	}

	@Override
	public void stop() {
		cache.cleanUp();
		cache.invalidateAll();

	}

	@Override
	public Object get(Object key) {
		return cache.getIfPresent(key);
	}

	@Override
	public void put(Object key, Object value) {
		cache.put(key, value);
	}

	@Override
	public void remove(Object key) {
		cache.invalidate(key);

	}

	@Override
	public long size() {
		return cache.size();
	}

	@Override
	public void clear() {
		cache.invalidateAll();
	}

	@Override
	public boolean contain(Object key) {
		if (get(key) != null)
			return true;
		else
			return false;
	}

	@Override
	public Collection keySet() {
		return cache.asMap().keySet();
	}

	@Override
	public long getCacheHits() {
		return cache.stats().hitCount();
	}

	@Override
	public long getCacheMisses() {
		return cache.stats().missCount();
	}

}
