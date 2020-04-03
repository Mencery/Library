package web.command.admin;

import db.DBManager;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;



@WebServlet("/block")
public class Blocking extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        DBManager dbManager = DBManager.getInstance();
        String action = req.getParameter("action");

        try {
            if("block".equals(action)) {
                dbManager.blockUser(login);

            }
            if("unblock".equals(action)){
               dbManager.unblockUser(login);
            }
        } catch (SQLException e) {
            req.getSession().setAttribute("cannotBlockUser", e);
        }
        req.getRequestDispatcher(Path.COMMAND_ADMIN).forward(req, resp);

    }
}