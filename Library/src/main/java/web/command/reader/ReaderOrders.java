package web.command.reader;

import db.DBManager;
import db.entity.Order;
import db.entity.User;
import exception.AppException;
import util.Path;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReaderOrders extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws  ServletException, AppException {
        DBManager dbManager = DBManager.getInstance();
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        try {
            List<Order> orders = dbManager.getOrderByUserId(currentUser);
            request.setAttribute("myOrders", orders);
        } catch (SQLException e) {
            request.getSession().setAttribute("cannotFindOrders", e);
        }
        return Path.PAGE_READER_PAGE;
    }
}
