package web.command.librarian;

import exception.AppException;
import util.Path;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LibrarianCommand extends Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        return Path.PAGE_LIBRARIAN_PAGE;
    }
}
