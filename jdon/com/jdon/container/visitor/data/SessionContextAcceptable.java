/*
 * Copyright 2003-2006 the original author or authors.
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
package com.jdon.container.visitor.data;

/**
 * this interface is implemented by the application services.
 * if they implements this interface, they will be set SessionContext value by
 * the SessionContext interceptor.
 * 
 * @author <a href="mailto:banqiao@jdon.com">banq</a>
 *
 */
public interface SessionContextAcceptable {

    void setSessionContext(SessionContext sessionContext);
    SessionContext getSessionContext();
    
}
