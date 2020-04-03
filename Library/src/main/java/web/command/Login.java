package web.command;

import db.DBManager;
import db.entity.User;
import exception.AppException;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends Command {
    private static final String ADMIN ="admin";
    private static final String LIBRARIAN="librarian";
    private static final String READER="reader";

    /**
     *
     * @param request - {@link HttpServletRequest}
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws {@link AppException}
     */
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();


        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");


        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new  AppException("Login/password cannot be empty");
        }
        DBManager dbManager = DBManager.getInstance();
        User user = null;
        try {
           user = dbManager.getUserByLogin(login) ;
        } catch (SQLException e) {
            response.getWriter().println("Technical error. The site is temporarily not working");
            return null;
        }

        String forward = Path.PAGE_ERROR_PAGE;
        if (user.getLogin() == null ) {
            request.getSession().setAttribute("NotExistsUser", "NotExistsUser");
            return Path.PAGE_LOGIN;
        }
        if (!password.equals(user.getPassword())) {
            request.getSession().setAttribute("WrongPassword", "WrongPassword");
            return  Path.PAGE_LOGIN;
        }

        if(user.getLogin() != null && user.getBlocked()==0 && password.equals(user.getPassword())) {
            request.getSession().setAttribute("currentUser",user);
            if (ADMIN.equals(user.getStatus())) {
                forward = Path.COMMAND_ADMIN;
            }
            if (LIBRARIAN.equals(user.getStatus())) {
                forward = Path.COMMAND_LIBRARIAN;
            }
            if (READER.equals(user.getStatus())) {
                forward = Path.COMMAND_READER;
            }
        }else {

            forward = Path.COMMAND_LOGOUT;
        }
        return forward;
    }
}
