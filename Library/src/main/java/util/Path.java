package util;

public class Path {
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_ADMIN_PAGE= "/WEB-INF/jsp/admin/admin_page.jsp";
    public static final String PAGE_ADD_BOOK_PAGE= "/WEB-INF/jsp/admin/add_book.jsp";
    public static final String PAGE_EDIT_BOOK_PAGE= "/WEB-INF/jsp/admin/edit_book.jsp";
    public static final String PAGE_NEW_LIBRARIAN_PAGE= "/WEB-INF/jsp/admin/newLibrarian.jsp";
    public static final String PAGE_LIBRARIAN_PAGE= "/WEB-INF/jsp/librarian/librarian_page.jsp";
    public static final String PAGE_CONFIRMATION_PAGE= "/WEB-INF/jsp/librarian/confirmation.jsp";
    public static final String PAGE_NEW_USER_PAGE= "new_user.jsp";
    public static final String PAGE_FORGET_PASSWORD_PAGE= "forget_password.jsp";

    public static final String PAGE_LOGIN= "login.jsp";
    public static final String PAGE_READER_PAGE= "/WEB-INF/jsp/reader/reader_page.jsp";

    public static final String COMMAND_LOGOUT= "controller?command=logout";

    public static final String COMMAND_FORGET_PASSWORD_COMMAND= "controller?command=forgetPassword";
    public static final String COMMAND_ADMIN= "/controller?command=admin";
    public static final String COMMAND_ADD_BOOK= "controller?command=addBook";
    public static final String COMMAND_EDIT_BOOK= "controller?command=editBook";
    public static final String COMMAND_NEW_LIBRARIAN= "controller?command=newLibrarian";
    public static final String COMMAND_READER= "/controller?command=reader";
    public static final String COMMAND_LIBRARIAN ="/controller?command=librarian";

    public static final String COMMAND_READER_ORDERS ="controller?command=reader_orders";
    public static final String COMMAND_NOT_CONFIRMED_ORDERS ="controller?command=not_confirmed_orders";
}
