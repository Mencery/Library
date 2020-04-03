package web.command.librarian;

import exception.AppException;
import util.Path;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmCommand extends Command {

    private static  int reader_id = 0;
    private static int book_id = 0;
    public static int getReader_id() {
        return reader_id;
    }

    public static int getBook_id() {
        return book_id;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws  ServletException, AppException {

            reader_id = Integer.parseInt(request.getParameter("reader_id"));
            book_id = Integer.parseInt(request.getParameter("book_id"));

        return Path.PAGE_CONFIRMATION_PAGE;
    }
}
