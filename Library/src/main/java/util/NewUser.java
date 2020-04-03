package util;

import db.DBManager;
import db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/new_user")
public class NewUser extends HttpServlet {
    User user;
    String login;
    String password;
    String phoneNumber;

    /**
     *
     * @param request - @link HttpServletRequest
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        user = new User();
        String status = request.getParameter("status");


        login = request.getParameter("login");
        password = request.getParameter("password");
        phoneNumber = request.getParameter("phoneNumber");

        if (login.length() > 0) {
            user.setLogin(login);
        } else {
            response.getWriter().println("You missed login");
            return;
        }
        if (password.length() > 0) {
            user.setPassword(password);
        } else {
            response.getWriter().println("You missed password");
            return;
        }
        if(phoneNumber.length()>0){

            user.setPhoneNumber(phoneNumber);

        }else {
            response.getWriter().println("You missed phone number");
            return;
        }

            user.setStatus(status);
        user.setBlocked(0);


        DBManager dbManager = DBManager.getInstance();
        try {
            dbManager.insertUser(user);
        } catch (SQLException e) {
            response.getWriter().println("Cannot add user. This user already exist");
            return;
        }
        if("reader".equals(status)){
            request.getRequestDispatcher
                    (Path.PAGE_LOGIN).forward(request, response);
        }
        if("librarian".equals(status)){
            request.getRequestDispatcher
                    (Path.COMMAND_ADMIN).forward(request, response);
        }
    }
}