package web.command.admin;

import db.DBManager;
import db.entity.Book;
import util.Path;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/new_book")
public class NewBook extends HttpServlet {
     private Book book;
     private final String SQL_ERROR = "<script> " +
             "alert(\"This book already exist\")</script>";



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        book = new Book();
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publishingOffice = req.getParameter("publishingOffice");
        String date = req.getParameter("date");
        String amount = req.getParameter("amount");
        int dateInt;
        int amountInt;
        if (name.length() > 0) {
            book.setName(name);
        } else {
            req.getSession().setAttribute("nameException", new NullPointerException());
            req.getRequestDispatcher(Path.PAGE_ADD_BOOK_PAGE).forward(req, resp);
        }
        if (author.length() > 0) {
            book.setAuthor(author);

        } else {
            req.getSession().setAttribute("authorException", new NullPointerException());
            req.getRequestDispatcher(Path.PAGE_ADD_BOOK_PAGE).forward(req, resp);
        }
        if (publishingOffice.length() > 0) {
            book.setPublishingOffice(publishingOffice);

        } else {
            req.getSession().setAttribute("officeException", new NullPointerException());
            req.getRequestDispatcher(Path.PAGE_ADD_BOOK_PAGE).forward(req, resp);
        }
        try {

            dateInt = Integer.parseInt(date);
            if (dateInt > 2019 || dateInt < 1000) {
                throw new NumberFormatException();
            } else {
                book.setDate(dateInt);
            }
        } catch (NumberFormatException ex) {
            req.getSession().setAttribute("dateException", ex);
            req.getRequestDispatcher(Path.PAGE_ADD_BOOK_PAGE).forward(req, resp);

        }
        try {

            amountInt = Integer.parseInt(amount);
            if (amountInt < 1) {
                throw new NumberFormatException();
            } else {
                book.setAmount(amountInt);
            }
        } catch (NumberFormatException ex) {
            req.getSession().setAttribute("amountException", ex);
            req.getRequestDispatcher(Path.PAGE_ADD_BOOK_PAGE).forward(req, resp);

        }

        DBManager dbManager = DBManager.getInstance();
        if ("add".equals(req.getParameter("page"))) {


        try {
            dbManager.insertBook(book);
        } catch (SQLException e) {
            resp.getWriter().println(SQL_ERROR);
        }
    }
        if("edit".equals(req.getParameter("page"))){
            try {
                dbManager.updateBook(book, req.getParameter("findname"));
            } catch (SQLException e) {
                resp.getWriter().println(SQL_ERROR);

            }
        }
        req.getRequestDispatcher(Path.COMMAND_ADMIN).forward(req, resp);


    }
}
