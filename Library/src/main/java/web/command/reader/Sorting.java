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
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/sorting")
public class Sorting extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager dbManager = DBManager.getInstance();

        List<Book> books =  dbManager.getAllBooks();

        String sortBy = req.getParameter("sort");

        switch (sortBy) {
            case "name":
                CompareByBookName compareByBookName =new CompareByBookName();
                Collections.sort(books,compareByBookName);
                break;
            case "author":
                CompareByBookAuthor compareByBookAuthor = new CompareByBookAuthor();
                Collections.sort(books,compareByBookAuthor);
                break;
            case "office":
                CompareByBookOffice compareByBookOffice = new CompareByBookOffice();
                Collections.sort(books,compareByBookOffice);
                break;
            case "year":
                CompareByBookYear compareByBookYear = new CompareByBookYear();
                Collections.sort(books, compareByBookYear);
                break;
            default:
                break;
        }
        req.setAttribute("catalog", books);
        req.getRequestDispatcher(Path.COMMAND_READER).forward(req, resp);

    }
    private static class CompareByBookName implements Comparator<Book>, Serializable {
        public int compare(Book book1,Book book2) {
            int result = String.CASE_INSENSITIVE_ORDER.compare(book1.getName(),book2.getName());
            if (result ==0) {
                result = book1.getName().compareTo(book2.getName());
            }
            return result;
        }
    }
    private static class CompareByBookAuthor implements Comparator<Book>, Serializable {
        public int compare(Book book1,Book book2) {
            int result = String.CASE_INSENSITIVE_ORDER.compare(book1.getAuthor(),book2.getAuthor());
            if (result ==0) {
                result = book1.getAuthor().compareTo(book2.getAuthor());
            }
            return result;
        }
    }
    private static class CompareByBookOffice implements Comparator<Book>, Serializable {
        public int compare(Book book1,Book book2) {
            int result = String.CASE_INSENSITIVE_ORDER.compare
                    (book1.getPublishingOffice(),book2.getPublishingOffice());
            if (result ==0) {
                result = book1.getPublishingOffice().compareTo(book2.getPublishingOffice());
            }
            return result;
        }
    }
    private static class CompareByBookYear implements Comparator<Book>, Serializable {
        public int compare(Book book1,Book book2) {
            if (book1.getDate() > book2.getDate()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
