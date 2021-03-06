package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tables.Book;

/**
 * Class for accessing database table for bookmarks of type 'Book'.
 */
public class BookDAO implements AbstractDAO<Book, Integer> {
    
    private final AbstractDatabase database;
    
    public BookDAO(AbstractDatabase db) {
        database = db;
    }
    
    /**
     * Adds a book to the database table 'Book'
     * 
     * @param book to be added
     * @return
     * @throws SQLException 
     */
    @Override
    public Book create(Book book) throws SQLException {
        database.update("INSERT INTO Book(title, author, ISBN) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getISBN());
        
        return book;
    }

    @Override
    public Book findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        Map<String, List<String>> results = database.query("SELECT * FROM Book");
        
        for (int i = 0; i < results.get(results.keySet().toArray()[0]).size(); i++) {
            Book book = new Book();
            for (String col : results.keySet()) {
                if (col.equals("title")) {
                    book.setTitle(results.get(col).get(i));
                } else if (col.equals("author")) {
                    book.setAuthor(results.get(col).get(i));
                } else if (col.equals("ISBN")) {
                    book.setISBN(results.get(col).get(i));
                }
            }
            books.add(book);
        }
        
        return books;
    }

    @Override
    public void update(Integer key, Book t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}