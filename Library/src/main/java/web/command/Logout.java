package web.command;

import exception.AppException;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, AppException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }


        return Path.PAGE_LOGIN;
    }
}
