/*
 * Copyright 2002-2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springframework.conversation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.conversation.ConversationEndingType;
import org.springframework.conversation.JoinMode;
import org.springframework.conversation.interceptor.ConversationAttribute;

/**
 * If this annotation is placed on a method, it will create a new conversation
 * using the specified {@link JoinMode} or {@link JoinMode#NEW} as the default.<br/>
 * The new conversation is created BEFORE the method itself is invoked (as a
 * before-advice) and the conversation is always ended AFTER the method was
 * invoked (as an after-advice), no matter if it was successful or not.<br/>
 * Placing this annotation on a method has most likely the same effect as
 * placing {@link BeginConversation} and {@link EndConversation} the same time
 * on the same method.<br/>
 * If a method should run within its own, isolated conversation, this annotation
 * might be handy using join mode {@link JoinMode#ISOLATED} so that a new,
 * isolated conversation is started and ended while executing the method and
 * switched back to the already ongoing conversation after the method has been
 * terminated.
 * 
 * @author Micha Kiener
 * @author Agim Emruli
 * @since 3.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Conversational {
	/**
	 * The {@link JoinMode} used for this conversation.
	 */
	JoinMode joinMode() default JoinMode.NEW;

	/**
	 * The timeout for this conversation.
	 */
	int timeout() default ConversationAttribute.DEFAULT_TIMEOUT;

	/**
	 * The {@link ConversationEndingType} for this conversation.
	 */
	ConversationEndingType endingType() default ConversationEndingType.SUCCESS;
}