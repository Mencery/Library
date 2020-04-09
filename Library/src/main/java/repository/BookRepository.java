package repository;


import db.entity.Book;
import db.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books;

    public BookRepository(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;

    }

    public List<Book> getByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        books.stream().filter(book -> author.equals(book.getAuthor())).forEach(result::add);
        return result;
    }
}
