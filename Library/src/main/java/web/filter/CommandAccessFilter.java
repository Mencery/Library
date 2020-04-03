package web.filter;

import db.Status;
import db.entity.User;
import util.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter  implements Filter {
    private Map<Status, List<String>> accessMap = new HashMap<Status, List<String>>();
    private List<String> commons = new ArrayList<String>();
    private List<String> outOfControl = new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init");
        accessMap.put(Status.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Status.LIBRARIAN, asList(filterConfig.getInitParameter("librarian")));
        accessMap.put(Status.READER, asList(filterConfig.getInitParameter("reader")));


        commons = asList(filterConfig.getInitParameter("common"));



        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (accessAllowed(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String errorMessage = "You do not have permission to access the requested resource";
            servletRequest.setAttribute("errorMessage", errorMessage);
            servletRequest.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }
    }

    private boolean accessAllowed(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String commandName = servletRequest.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

       User currentUser = (User)session.getAttribute("currentUser");
        Status status = Status.getStatus(currentUser);
        if (status == null) {
            return false;
        }

        return accessMap.get(status).contains(commandName)
                || commons.contains(commandName);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroy");
    }
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }}
