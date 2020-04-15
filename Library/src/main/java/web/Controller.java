package web;

import exception.AppException;
import org.apache.log4j.Logger;
import util.Path;
import web.command.Command;
import web.command.CommandContainer;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }


    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter("command");
        String forward = Path.PAGE_ERROR_PAGE;

        Command command = CommandContainer.getCommand(commandName);


        try {
            forward = command.execute(request, response);
        } catch (AppException e) {
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }
}
