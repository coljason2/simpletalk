package com.simpletalk.servlet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.directwebremoting.Browser;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.ui.dwr.Util;
import org.springframework.stereotype.Service;

/**
 * chat server
 */
@Slf4j
@Service("JavaChat")
@RemoteProxy(name = "JavaChat")
public class JavaChat {
    private final LinkedList<Message> messages = new LinkedList<Message>();
    private final Set<HttpSession> sessionSet = new HashSet<HttpSession>();

    @RemoteMethod
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
    }

    ;

    @RemoteMethod
    public void loginChat() {
        log.info("[SimpleTalk] add session = {}", getSession());
        sessionSet.add(getSession());
        log.info("[SimpleTalk] sessionSet size = {}", sessionSet.size());
        Util.setValue("allUsers", "在線人數:" + sessionSet.size());
    }

    @RemoteMethod
    public void logoutChat() {
        log.info("[SimpleTalk] delete session = {} ", getSession());
        sessionSet.remove(getSession());
        getSession().removeAttribute("username");
        log.info("[SimpleTalk] sessionSet size = {} ", sessionSet.size());
        Util.setValue("allUsers", "在線人數:" + sessionSet.size());
    }
}
