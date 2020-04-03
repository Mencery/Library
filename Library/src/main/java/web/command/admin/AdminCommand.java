package web.command.admin;

import db.DBManager;
import db.entity.Book;
import db.entity.User;
import exception.AppException;
import util.Path;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdminCommand  extends Command {

    CompareByUserId compare = new CompareByUserId();
    CompareByBookId compareBook = new CompareByBookId();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {


        List<User> users = DBManager.getInstance().getAllUsers();
        List<Book> books = DBManager.getInstance().getAllBooks();

        Collections.sort(users, compare);
        Collections.sort(books,  compareBook);

        request.setAttribute("users", users);
        request.setAttribute("books", books);
        return Path.PAGE_ADMIN_PAGE;
    }
    private static class CompareByUserId implements Comparator<User>, Serializable {
        public int compare(User user1, User user2) {
            if (user1.getId() > user2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    private static class CompareByBookId implements Comparator<Book>, Serializable {
        public int compare(Book book1,Book book2) {
            if (book1.getId() > book2.getId()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}


