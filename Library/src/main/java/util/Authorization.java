package util;


import exception.AppException;
import web.command.Command;
import web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/authorization")
public class Authorization extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("newUser") != null) {
            response.sendRedirect("new_user.jsp");
        }
        if (request.getParameter("forgetPassword") != null) {
            response.sendRedirect("forget_password.jsp");
        }
        if(request.getParameter("logIn") != null){
            Command command = CommandContainer.getCommand("login");
            String forward = Path.PAGE_ERROR_PAGE;
            try {
                forward = command.execute(request,response);

            } catch (AppException e) {
                request.setAttribute("errorMessage", e.getMessage());
            }

            request.getRequestDispatcher(forward).forward(request, response);

        }


    }
}
