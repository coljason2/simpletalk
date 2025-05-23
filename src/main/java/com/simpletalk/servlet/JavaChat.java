package com.simpletalk.servlet;

import java.util.*;

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
    private static final LinkedList<Message> messages = new LinkedList<Message>();
    private static final Set<HttpSession> sessionSet = new HashSet<HttpSession>();
    private static final Map<HttpSession, String> sessionMap = new HashMap<>();

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

    @RemoteMethod
    public void loginChat() {
        HttpSession session = getSession();
        String username = (String) session.getAttribute("username");
        if (username == null || username.trim().isEmpty()) {
            username = "訪客_" + session.getId().substring(0, 5); // fallback
            session.setAttribute("username", username);
        }

        sessionMap.put(session, username);
        log.info("[SimpleTalk] add session = {} (username: {})", session, username);
        // 推送到所有頁面
        Browser.withAllSessions(() -> {
            Util.setValue("allUsers", "在線人數:" + sessionMap.size());
            Util.setValue("userList", getAllUsernames());
        });
    }

    @RemoteMethod
    public void logoutChat() {
        HttpSession session = getSession();
        sessionMap.remove(session);
        session.removeAttribute("username");
        log.info("[SimpleTalk] remove session = {}", session);
// 推送到所有頁面
        Browser.withAllSessions(() -> {
            Util.setValue("allUsers", "在線人數:" + sessionMap.size());
            Util.setValue("userList", getAllUsernames());
        });
    }

    private String getAllUsernames() {
        StringBuilder sb = new StringBuilder();
        for (String name : sessionMap.values()) {
            sb.append(name).append("<br/>");
        }
        return sb.toString();
    }
}
