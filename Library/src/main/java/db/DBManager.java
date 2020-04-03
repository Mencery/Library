package db;

import db.entity.Book;
import db.entity.Order;
import db.entity.User;
import org.apache.log4j.Logger;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    private static final String SQL_INSERT_USER =
            "INSERT INTO library.users " +
                    "(login, password, status, phoneNumber,blocked) VALUES (?, ?, ?, ?,?)";

    private static final String SQL_PASSWORD_USER =
            "SELECT password FROM library.users WHERE login = ? and phoneNumber = ?";
    private static final String SQL_GET_USER_BY_LOGIN =
            "SELECT * from users\n" +
                    " WHERE login = ?";
    private static final String SQL_GET_ALL_USERS =
            "SELECT * from users ";
    private static final String SQL_GET_ALL_BOOKS =
            "SELECT * FROM library.books";
    private static final String SQL_INSERT_BOOK =
            "INSERT INTO library.books " +
                    "(name,author, publishingOffice, date,amount) VALUES (?, ?, ?, ?,?)";
    private static final String SQL_DELETE_BOOK = "DELETE FROM library.books" +
            " WHERE name =?";
    private static final String SQL_UPDATE_BOOK =
            "UPDATE library.books SET name =?,author =?, publishingOffice =?, date =?,amount =? Where name =?";
    private static final String SQL_DELETE_LIBRARIAN = "DELETE FROM library.users" +
            " WHERE login =?";
    private static final String SQL_BLOCKED_USER =
            "UPDATE users SET blocked =1  Where login =?";
    private static final String SQL_UNBLOCKED_USER =
            "UPDATE users SET blocked =0  Where login =?";
    private static final String SQL_GET_BOOK_BY_NAME =
            "SELECT * from books\n" +
                    " WHERE name = ?";
    private static final String SQL_GET_BOOK_BY_AUTHOR = "SELECT * from books\n" +
            " WHERE author = ?";
    private static final String SQL_SET_READER_ORDER = "INSERT INTO reader_orders" +
            "(reader_id, book_id,confirm) VALUES(?,?,0)";
    private static final String SQL_GET_ORDER_BY_USER_ID = "SELECT reader_orders.*, " +
            "name, author,login, DATEDIFF( return_date, ?) daysLeft " +
            "from reader_orders" +
            " inner join books  on reader_orders.book_id = books.id " +
            "inner join users  on reader_orders.reader_id = users.id where reader_id =?";
    private static final String SQL_GET_NOT_CONFIRMED_ORDERS = "SELECT reader_orders.*, name, author,login from reader_orders" +
            " inner join books  on reader_orders.book_id = books.id " +
            "inner join users  on reader_orders.reader_id = users.id where confirm =0";
    private static final String SQL_GET_CONFIRMED_ORDERS = "SELECT reader_orders.*, name, author,login," +
            "DATEDIFF( return_date,\n" +
            "    ?)daysLeft" +
            " from reader_orders" +
            " inner join books  on reader_orders.book_id = books.id " +
            "inner join users  on reader_orders.reader_id = users.id where confirm =1";
    private static final String SQL_CONFIRM_ORDER =
            "UPDATE reader_orders SET confirm=1,return_date =? Where reader_id =? and book_id =?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM reader_orders" +
            " WHERE reader_id = ? and book_id = ?";
    private static final String SQL_PICK_UP_BOOK = "UPDATE books SET amount = " +
            "amount-1 Where id =?";
    private static final String SQL_RETURN_BOOK = "UPDATE books SET amount = " +
            "amount+1 Where id =?";
    private static final String SQL_GET_FORFEITS = "SELECT forfeit " +
            "from forfeits WHERE days=?";
    private static final String LESS_THEN_7 = "<7";
    private static final String LESS_THEN_30 = "<30";
    private static final String LESS_THEN_90 = "<90";
    private static final String LESS_THEN_365 = "<365";
    private static final String MORE_THEN_365 = ">365";

    private DBManager() {
    }


    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private static Connection getConnection() throws SQLException {

        Connection con = DBUtil.getDataSource().getConnection();


        return con;
    }

    private static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id")); // <-- Constants.FIELD_USERS_ID
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setStatus(rs.getString("status"));
        user.setPhoneNumber(rs.getString("phoneNumber"));
        user.setBlocked(rs.getInt("blocked"));

        return user;
    }

    private static Book extractBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));

        book.setPublishingOffice(rs.getString("publishingOffice"));
        book.setDate(rs.getInt("date"));
        book.setAmount(rs.getInt("amount"));
        return book;
    }

    private static Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setReaderId(rs.getInt("reader_id"));
        order.setBookId(rs.getInt("book_id"));

        order.setBookName(rs.getString("name"));
        order.setBookAuthor(rs.getString("author"));
        order.setReaderLogin(rs.getString("login"));

        try {
            order.setDaysLeft(rs.getInt("daysLeft"));
        } catch (SQLException e) {
            System.out.println("not confirm order");
        }


        order.setReturnDate(rs.getString("return_date"));
        order.setConfirm(rs.getInt("confirm"));
        return order;
    }

    /**
     *
     * @param rs
     * @param user
     * @param con
     * @return
     * @throws SQLException
     */
    private static Order extractOrderWithForfeits(ResultSet rs, User user, Connection con) throws SQLException {
        Order order = new Order();
        order.setReaderId(rs.getInt("reader_id"));
        order.setBookId(rs.getInt("book_id"));

        order.setBookName(rs.getString("name"));
        order.setBookAuthor(rs.getString("author"));
        order.setReaderLogin(rs.getString("login"));


        order.setDaysLeft(rs.getInt("daysLeft"));


        order.setReturnDate(rs.getString("return_date"));
        order.setConfirm(rs.getInt("confirm"));

        ResultSet forfeitsRS;
        PreparedStatement pstmt = con.prepareStatement(SQL_GET_FORFEITS);
        int daysLeft = Math.abs(order.getDaysLeft());

        if (daysLeft < 7) {
            pstmt.setString(1, LESS_THEN_7);

        }
        if (daysLeft >= 7 && daysLeft < 30) {
            pstmt.setString(1, LESS_THEN_30);
        }
        if (daysLeft >= 30 && daysLeft < 90) {
            pstmt.setString(1, LESS_THEN_90);
        }
        if (daysLeft >= 90 && daysLeft < 365) {
            pstmt.setString(1, LESS_THEN_365);
        }
        if (daysLeft >= 365) {
            pstmt.setString(1, MORE_THEN_365);
        }

        forfeitsRS = pstmt.executeQuery();
        if (forfeitsRS.next()) {
            order.setForfeits(forfeitsRS.getString("forfeit"));
        }
        user.setHasForfeits(true);


        return order;
    }

    public void insertUser(User user) throws SQLException {


        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getStatus());
            pstmt.setString(k++, user.getPhoneNumber());
            pstmt.setInt(k++, user.getBlocked());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                // not "id"
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);

                }

            }


        } finally {
            con.close();
        }
    }

    public void deleteLibrarian(String login) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_DELETE_LIBRARIAN);

            pstmt.setString(1, login);


            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

    public String getPassword(String login, String phoneNumber) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        String password = "";

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_PASSWORD_USER);
            int k = 1;
            pstmt.setString(k++, login);
            pstmt.setString(k++, phoneNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                password = rs.getString("password");
            }


        } finally {
            con.close();
        }
        return password;
    }

    public User getUserByLogin(String login) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        User user = new User();

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_GET_USER_BY_LOGIN);

            pstmt.setString(1, login);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                user = extractUser(rs);
            }


        } finally {
            con.close();
        }
        return user;
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_ALL_USERS);
            while (rs.next()) {
                users.add(extractUser(rs));
            }

        } catch (SQLException e) {
            rollback(con);
            LOG.error(SQL_GET_ALL_USERS, e);

            e.printStackTrace();
        }


        return users;
    }

    public void blockUser(String login) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_BLOCKED_USER);

            pstmt.setString(1, login);

            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

    public void unblockUser(String login) throws SQLException {

        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_UNBLOCKED_USER);

            pstmt.setString(1, login);

            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }


    public void insertBook(Book book) throws SQLException {


        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_INSERT_BOOK, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, book.getName());
            pstmt.setString(k++, book.getAuthor());
            pstmt.setString(k++, book.getPublishingOffice());
            pstmt.setInt(k++, book.getDate());
            pstmt.setInt(k++, book.getAmount());


            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    book.setId(id);
                }

            }


        } finally {
            con.close();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_ALL_BOOKS);
            while (rs.next()) {
                books.add(extractBook(rs));
            }

        } catch (SQLException e) {
            rollback(con);
            LOG.error(SQL_GET_ALL_USERS, e);

            e.printStackTrace();
        }


        return books;
    }

    public void deleteBook(String name) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_DELETE_BOOK);

            pstmt.setString(1, name);


            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

    public void updateBook(Book book, String findName) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_UPDATE_BOOK);
            int k = 1;
            pstmt.setString(k++, book.getName());
            pstmt.setString(k++, book.getAuthor());
            pstmt.setString(k++, book.getPublishingOffice());
            pstmt.setInt(k++, book.getDate());
            pstmt.setInt(k++, book.getAmount());
            pstmt.setString(k++, findName);
            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

    public Book getBookByName(String name) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Book book = new Book();

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_GET_BOOK_BY_NAME);

            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                book = extractBook(rs);
            }


        } finally {
            con.close();
        }
        return book;
    }

    public List<Book> getBooksByAuthor(String author) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        List<Book> books = new ArrayList<Book>();

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_GET_BOOK_BY_AUTHOR);

            pstmt.setString(1, author);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                books.add(extractBook(rs));
            }

        } finally {
            con.close();
        }
        return books;
    }

    public void setOrder(int reader_id, int book_id) throws SQLException {


        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_SET_READER_ORDER);
            int k = 1;
            pstmt.setInt(k++, reader_id);
            pstmt.setInt(k++, book_id);


            pstmt.executeUpdate();


        } finally {
            con.close();
        }
    }

    public List<Order> getOrderByUserId(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        ResultSet rs;
        List<Order> orders = new ArrayList<Order>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date now = new Date();

        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_GET_ORDER_BY_USER_ID);
            int k = 1;
            pstmt.setString(k++, sdf.format(now));
            pstmt.setInt(k++, user.getId());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("daysLeft") < 0) {
                    orders.add(extractOrderWithForfeits(rs, user, con));
                } else {
                    orders.add(extractOrder(rs));
                }
            }

        } finally {
            con.close();
        }
        return orders;
    }
    public List<Order> getPenaltyOrders() throws SQLException {
        List<Order> orders = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date now = new Date();


        try {
            con = getConnection();

            pstmt = con.prepareStatement(SQL_GET_CONFIRMED_ORDERS);

            pstmt.setString(1, sdf.format(now));



            rs = pstmt.executeQuery();
            while (rs.next()) {

                if (rs.getInt("daysLeft") <= -20) {
                    orders.add(extractOrder(rs));
                }
            }

        } finally {
            con.close();
        }
        return orders;
    }

    public List<Order> getAllNotConfirmedOrders() {
        List<Order> orders = new ArrayList<Order>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_GET_NOT_CONFIRMED_ORDERS);
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }

        } catch (SQLException e) {
            rollback(con);

            e.printStackTrace();
        }


        return orders;
    }

    public List<Order> getAllConfirmedOrders() {
        List<Order> orders = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date now = new Date();


        try {
            con = getConnection();

            pstmt = con.prepareStatement(SQL_GET_CONFIRMED_ORDERS);

            pstmt.setString(1, sdf.format(now));

            rs = pstmt.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }


        } catch (SQLException e) {
            rollback(con);

            e.printStackTrace();
        }


        return orders;
    }

    public void confirmOrder(String date, int readerId, int bookId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_CONFIRM_ORDER);
            int k = 1;

            pstmt.setString(k++, date);
            pstmt.setInt(k++, readerId);
            pstmt.setInt(k++, bookId);
            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

    public void deleteOrder(int readerId, int bookId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_DELETE_ORDER);
            int k = 1;
            pstmt.setInt(k++, readerId);
            pstmt.setInt(k++, bookId);


            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

   public void returnBook(int bookId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_RETURN_BOOK);


            pstmt.setInt(1, bookId);

            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

    public void PickUpBook(int bookId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt;
        try {
            con = getConnection();


            pstmt = con.prepareStatement(SQL_PICK_UP_BOOK);


            pstmt.setInt(1, bookId);

            pstmt.executeUpdate();


        } finally {
            con.close();
        }

    }

}
