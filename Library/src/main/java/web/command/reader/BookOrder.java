package web.command.reader;


import db.DBManager;
import db.entity.User;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/order")
public class BookOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager dbManager = DBManager.getInstance();
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        try {
            dbManager.setOrder(currentUser.getId(),bookId);
        } catch (SQLException e) {
            req.getSession().setAttribute("doubleOrderException", e);

        }
        req.getRequestDispatcher(Path.COMMAND_READER).forward(req, resp);


    }
}