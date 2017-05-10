import java.sql.*;
import java.util.ArrayList;

public class dbController {

    public static ArrayList<Book> loadBook(){

        try{
            sqlConnect.createConnection(); //connect to db
            String allBooks = "SELECT * FROM Book"; //select anything from book database
            Statement statement = sqlConnect.conn.createStatement();
            ResultSet allBooksrs = statement.executeQuery(allBooks);
            ArrayList<Book> books = new ArrayList<>(); //refer to book class

            while (allBooksrs.next()){
                String bookTitle = allBooksrs.getString("title");
                String bookAuthor = allBooksrs.getString("author");
                int bookRating = allBooksrs.getInt("rating");
                String bookReview = allBooksrs.getString("review");
                Book book = new Book(bookTitle, bookAuthor, bookRating, bookReview);
                books.add(book); //creating book object to hold variables
            }
            sqlConnect.killConnection(); //disconnect
            return books;
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return null;
    }

    public static void newBook(String bookTitle, String bookAuthor, int bookRating, String bookReview){
//method to connect, use insert statement on each parameter in database, add to list
        try {
            sqlConnect.createConnection();
            String newStatement = "INSERT INTO Book VALUES (?, ?, ?, ?)";
            PreparedStatement query = sqlConnect.conn.prepareStatement(newStatement);

            query.setString(1, bookTitle);
            query.setString(2, bookAuthor);
            query.setInt(3, bookRating);
            query.setString(4, bookReview);

            ArrayList<Book> newBook = new ArrayList<>();
            Book book = new Book(bookTitle, bookAuthor, bookRating, bookReview);
            newBook.add(book);
            query.executeUpdate();
            sqlConnect.killConnection();
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void removeBook(Book book){ //method to connect to db, delete from each parameter of database

        try{
            sqlConnect.createConnection();
            String removeStatement = "DELETE FROM Book WHERE title = ?" +
                    " AND author = ?" +
                    " AND rating = ?" +
                    " AND review = ?"; //writes to database delete statement
            PreparedStatement delquery = sqlConnect.conn.prepareStatement(removeStatement);

            delquery.setString(1, book.getTitle()); //set indexes to appropriate parameters
            delquery.setString(2, book.getAuthor());
            delquery.setInt(3, book.getRating());
            delquery.setString(4, book.getReview());

            delquery.executeUpdate();
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
