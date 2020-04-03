package web.command.librarian;

import db.DBManager;
import db.entity.Order;
import exception.AppException;
import util.Path;
import web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class NotConfirmedOrders extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        DBManager dbManager = DBManager.getInstance();


            List<Order> orders = dbManager.getAllNotConfirmedOrders();
            request.setAttribute("notConfirmedOrders", orders);

        return Path.PAGE_LIBRARIAN_PAGE;
    }
}
