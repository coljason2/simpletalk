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
package com.simpletalk;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	private String username;
	private String text;
	private String date;
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Message(String newtext) {
		text = newtext;
		if (text.length() > 256) {
			text = text.substring(0, 256);
		}
		date = sdFormat.format(new Date());
	}

	public String getText() {
		return text;
	}

	public String getDate() {
		return date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
