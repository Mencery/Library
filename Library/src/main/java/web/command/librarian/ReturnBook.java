package web.command.librarian;

import db.DBManager;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/*
*@author Denys Plekhanov
 */
@WebServlet("/returned")
public class ReturnBook  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager dbManager = DBManager.getInstance();
        int readerId = Integer.parseInt(req.getParameter("reader_id"));
        int bookId = Integer.parseInt(req.getParameter("book_id"));
        try {
            dbManager.deleteOrder(readerId,bookId);
            dbManager.returnBook(bookId);
        } catch (SQLException e) {
            req.getSession().setAttribute("cannotReturnBook", e);
        }
        req.getRequestDispatcher(Path.COMMAND_LIBRARIAN).forward(req, resp);
    }


}
