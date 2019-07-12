package dao;

import entity.Book;
import entity.Publisher;
import java.sql.*;
import java.util.ArrayList;

public class BookDAO extends DAO {

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> list = new ArrayList<>();
        String getAllQuery = "select book.bookid, book.booktitle, book.bookprice, book.bookquantity,publisher.*\n"
                + "from book join publisher on book.publisher_id = publisher.publisherid";
        openConnection();
        try {
            ps = con.prepareStatement(getAllQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                int bookId = rs.getInt(1);
                String bookTitle = rs.getString(2);
                float bookPrice = rs.getFloat(3);
                int bookQuantity = rs.getInt(4);
                int pubId = rs.getInt(5);
                String pubName = rs.getString(6);
                String addr = rs.getString(7);

                Publisher pub = new Publisher(pubId, pubName, addr);
                Book book = new Book(bookId, bookTitle, bookPrice, bookQuantity, pub);
                list.add(book);
            }
            System.out.println("get all books done");
        } catch (SQLException ex) {
            System.out.println("Error when get all books");
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Publisher> getAllPublishers() {
        ArrayList<Publisher> list = new ArrayList<>();
        String getAllQuery = "select * from publisher";
        openConnection();
        try {
            ps = con.prepareStatement(getAllQuery);
            rs = ps.executeQuery();

            while (rs.next()) {
                int pubId = rs.getInt(1);

                String pubName = rs.getString(2);

                String addr = rs.getString(3);

                Publisher pub = new Publisher(pubId, pubName, addr);

                list.add(pub);               
            }

        } catch (SQLException ex) {
            System.out.println("Error when get all publishers");
        } finally {
            closeConnection();

        }

        return list;
    }

    public int findIndex(String name) {
        ArrayList<Publisher> publishers = getAllPublishers();
        for (int i = 0; i < publishers.size(); i++) {
            if (name.equals(publishers.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
}
