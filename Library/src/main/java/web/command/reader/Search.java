package web.command.reader;


import db.DBManager;
import db.entity.Book;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/search")
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("s");
        DBManager dbManager = DBManager.getInstance();

        try {
            List<Book> books = dbManager.getBooksByAuthor(search) ;
            books.add(dbManager.getBookByName(search));
            req.setAttribute("searchedBooks", books);
        } catch (SQLException e) {
            req.getSession().setAttribute("cannotFindBooks", e);
        }
        req.getRequestDispatcher(Path.COMMAND_READER).forward(req, resp);


    }
}
