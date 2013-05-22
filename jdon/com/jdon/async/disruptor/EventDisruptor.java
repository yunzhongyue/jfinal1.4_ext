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
package com.jdon.async.disruptor;

import com.jdon.domain.message.DomainMessage;

/**
 * A subscriber send a EventDisruptor to RingBuffer.
 * 
 * Consumers will get the EventDisruptor with its OnEvent method from RingBuffer
 * 
 * @author banq
 * 
 */
public class EventDisruptor {

	protected String topic;

	protected DomainMessage domainMessage;

	public EventDisruptor() {

	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public DomainMessage getDomainMessage() {
		return domainMessage;
	}

	public void setDomainMessage(DomainMessage domainMessage) {
		this.domainMessage = domainMessage;
	}

}
