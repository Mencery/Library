package web.command.librarian;

import db.DBManager;
import db.entity.Order;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/confirmedOrders")
public class ConfirmedOrders extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager dbManager = DBManager.getInstance();
        List<Order> orders = dbManager.getAllConfirmedOrders();
        req.setAttribute("confirmedOrders", orders);
        req.getRequestDispatcher(Path.COMMAND_LIBRARIAN).forward(req, resp);
    }
}
