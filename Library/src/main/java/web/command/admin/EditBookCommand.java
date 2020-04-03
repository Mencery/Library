package web.command.admin;


import exception.AppException;
import util.Path;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditBookCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws AppException {
        req.getSession().setAttribute("editNameBook",req.getParameter("name"));
        req.getSession().setAttribute("editAuthorBook", req.getParameter("author"));
        req.getSession().setAttribute("editOfficeBook",req.getParameter("office"));
        req.getSession().setAttribute("editDateBook", req.getParameter("date"));
        req.getSession().setAttribute("editAmountBook", req.getParameter("amount"));


        return Path.PAGE_EDIT_BOOK_PAGE;
    }
}