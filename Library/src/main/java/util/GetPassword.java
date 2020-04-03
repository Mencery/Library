package util;

import db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/forget_password")
public class GetPassword extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String number = request.getParameter("phoneNumber");
        String password = "";
        DBManager dbManager = DBManager.getInstance();
        try {
            password = dbManager.getPassword(login, number);
        } catch (SQLException e) {
            response.getWriter().printf("The site is temporarily down.%nTry again later...");
        }
        if (password.length() > 0) {
            response.getWriter().printf("Password:%s%n", password);
        } else {

            response.getWriter().printf("Cannot find your password.%nCheck login and phoneNumber");
        }


    }

}
