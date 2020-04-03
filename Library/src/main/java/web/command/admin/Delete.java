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

@WebServlet("/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        DBManager dbManager = DBManager.getInstance();
        String item = req.getParameter("item");

        try {
            if("book".equals(item)) {
                dbManager.deleteBook(name);
            }
            if("librarian".equals(item)){
                dbManager.deleteLibrarian(name);
            }
        } catch (SQLException e) {
            req.getSession().setAttribute("cannotDeleteLibrarian", e);
        }
        req.getRequestDispatcher(Path.COMMAND_ADMIN).forward(req, resp);

    }
}
