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
package com.jdon.container.visitor.http;

import java.security.Principal;

import com.jdon.container.visitor.data.SessionContext;
import com.jdon.container.visitor.data.SessionContextSetup;
import com.jdon.controller.context.RequestWrapper;
import com.jdon.util.Debug;

public class HttpRequestUserSetup implements SessionContextSetup {

	private final static String module = HttpRequestUserSetup.class.getName();

	public void setup(SessionContext sessionContext, RequestWrapper request) {
		String remoteAddress = request.getRemoteAddr();
		saveSessionContext(REMOTE_ADDRESS, remoteAddress, sessionContext);

		Principal principal = request.getRegisteredPrincipal();
		if (principal == null)
			return;
		String username = principal.getName();
		saveSessionContext(PRINCIPAL_NAME, username, sessionContext);

		Debug.logVerbose("[JdonFramework] set principal name:" + username, module);
	}

	public String getPrincipalName(SessionContext sessionContext) {
		return (String) getArrtibute(PRINCIPAL_NAME, sessionContext);
	}

	public void saveSessionContext(String arrtibuteName, String arrtibuteValue, SessionContext sessionContext) {
		sessionContext.setArrtibute(arrtibuteName, arrtibuteValue);
		Debug.logVerbose("[JdonFramework] setArrtibute:" + arrtibuteName + "=" + arrtibuteValue, module);
	}

	public Object getArrtibute(String arrtibuteName, SessionContext sessionContext) {
		if (sessionContext == null) {
			Debug.logVerbose("[JdonFramework] sessionContext not be setup, and is null", module);
			return null;
		}
		return sessionContext.getArrtibute(arrtibuteName);
	}
}
