package db.entity;

public class Order {
    private int readerId;
    private int bookId;
    private String bookName;



    private String readerLogin;
    private String bookAuthor;
    private String returnDate;
    private int confirm;
    private  int daysLeft;
    private String forfeits;
    public String getForfeits() {
        return forfeits;
    }

    public void setForfeits(String forfeits) {
        this.forfeits = forfeits;
    }


    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }



    public String getReaderLogin() {
        return readerLogin;
    }

    public void setReaderLogin(String readerLogin) {
        this.readerLogin = readerLogin;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }


    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }


}
