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
package com.jdon.container.builder;

import com.jdon.container.ContainerWrapper;
import com.jdon.container.access.xml.AppConfigureCollection;

public class ContainerRegistry {

	protected final ContainerWrapper containerWrapper;

	public ContainerRegistry(ContainerWrapper containerWrapper) {
		this.containerWrapper = containerWrapper;
	}

	/**
	 * register at first before xml and annotation
	 */
	public void registerAppRoot() {
		containerWrapper.register(AppConfigureCollection.NAME, AppConfigureCollection.class);
		containerWrapper.register(StartablecomponentsRegistry.NAME, StartablecomponentsRegistry.class);
	}

}