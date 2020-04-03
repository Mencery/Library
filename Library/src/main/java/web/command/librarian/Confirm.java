package web.command.librarian;

import db.DBManager;
import util.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/confirmation")
public class Confirm extends HttpServlet {

    String issue;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("issue") != null) {
            issue = req.getParameter("issue");
        }


        if (req.getParameter("confirm") == null) {


            if ("Subscription".equals(issue)) {
                req.getSession().setAttribute("confirmType", "Subscription");
            }
            if ("To the hall".equals(issue)) {
                req.getSession().setAttribute("confirmType", "hall");
            }
            req.getRequestDispatcher(Path.PAGE_CONFIRMATION_PAGE).forward(req, resp);

        } else {
            StringBuilder date = new StringBuilder();
            if ("Subscription".equals(issue)) {
                int year = 0;
                int month =0;
                int day = 0;
                try {
                    year = Integer.parseInt(req.getParameter("year"));
                    if (year < 2019 || year > 2050  ) {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException ex) {
                    req.getSession().setAttribute("yearException", ex);
                    req.getRequestDispatcher(Path.PAGE_CONFIRMATION_PAGE).forward(req, resp);

                }


                try {
                    month = Integer.parseInt(req.getParameter("month"));
                    if (month < 1 || month > 12) {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException ex) {
                    req.getSession().setAttribute("monthException", ex);
                    req.getRequestDispatcher(Path.PAGE_CONFIRMATION_PAGE).forward(req, resp);

                }

                try {
                    day = Integer.parseInt(req.getParameter("day"));
                    if (day < 1 || day > 31) {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException ex) {
                    req.getSession().setAttribute("dayException", ex);
                    req.getRequestDispatcher(Path.PAGE_CONFIRMATION_PAGE).forward(req, resp);

                }
                date.append(year).append("-")
                        .append(month).append("-").append(day);


            }
            if ("To the hall".equals(issue)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date now = new Date();
                date.append(sdf.format(now));

            }
            DBManager dbManager = DBManager.getInstance();
            try {
                dbManager.confirmOrder(date.toString(),
                        ConfirmCommand.getReader_id(), ConfirmCommand.getBook_id());
                dbManager.PickUpBook(ConfirmCommand.getBook_id());


            } catch (SQLException e) {
                req.getSession().setAttribute("SQLException", e);
            }
            req.getRequestDispatcher(Path.COMMAND_LIBRARIAN).forward(req, resp);
        }
    }
}
