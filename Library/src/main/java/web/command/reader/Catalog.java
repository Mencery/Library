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


@WebServlet("/catalog")
public class Catalog extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBManager dbManager = DBManager.getInstance();

        List<Book> books = dbManager.getAllBooks() ;

        req.setAttribute("catalog", books);
        req.getRequestDispatcher(Path.COMMAND_READER).forward(req, resp);


    }
}
