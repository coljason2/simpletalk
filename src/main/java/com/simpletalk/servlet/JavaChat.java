/*
 * Copyright 2005 Joe Walker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.simpletalk.servlet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.directwebremoting.Browser;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.ui.dwr.Util;

/**
 * 
 * chat server
 * 
 */
@Slf4j
public class JavaChat {
	private final LinkedList<Message> messages = new LinkedList<Message>();
	private final Set<HttpSession> sessionSet = new HashSet<HttpSession>();

	public void addMessage(String text) {
		// Make sure we have a list of the list 10 messages
		if (text != null && text.trim().length() > 0) {
			if ("clearAll".equals(text)) {
				messages.clear();
				Util.setValue("text", "");
				Util.setValue("chatlog", "");
				return;
			}
			Message message = new Message(text);
			message.setUsername(getSession().getAttribute("username").toString());
			messages.addLast(message);
		}
		// Clear the input box in the browser that kicked off this page only
		Util.setValue("text", "");

		// For all the browsers on the current page:
		Browser.withCurrentPage(new Runnable() {
			public void run() {
				StringBuilder text = new StringBuilder();
				for (Message m : messages) {
					String content = m.getDate() + " [" + m.getUsername() + "]：" + m.getText();
					text.append(content).append("\n");
				}
				Util.setValue("chatlog", "");
				Util.setValue("chatlog", text.toString());
			}
		});
	}

	private HttpSession getSession() {
		WebContext ctx = WebContextFactory.get();
		return ctx.getSession();
	};

	public void loginChat() {
		log.info("--------add session = {}--------", getSession());
		sessionSet.add(getSession());
		log.info("--------sessionSet size = {}----------", sessionSet.size());
		Util.setValue("allUsers", "在線人數:" + sessionSet.size());
	}

	public void logoutChat() {
		log.info("--------delete session = {}--------", getSession());
		sessionSet.remove(getSession());
		getSession().removeAttribute("username");
		log.info("--------sessionSet size = {}----------", sessionSet.size());
		Util.setValue("allUsers", "在線人數:" + sessionSet.size());
	}
}
