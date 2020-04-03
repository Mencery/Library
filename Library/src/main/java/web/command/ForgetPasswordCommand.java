package web.command;

import exception.AppException;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgetPasswordCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, AppException {
        return Path.PAGE_FORGET_PASSWORD_PAGE;
    }
}
