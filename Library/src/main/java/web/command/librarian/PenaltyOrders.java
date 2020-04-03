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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orderPenalty")

public class PenaltyOrders extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager dbManager = DBManager.getInstance();

        List<Order> orders = null;
        try {
            orders = dbManager.getPenaltyOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("penaltyOrders", orders);
        req.getRequestDispatcher(Path.COMMAND_LIBRARIAN).forward(req, resp);
    }
}