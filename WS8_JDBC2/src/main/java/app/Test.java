package app;

import dao.PublisherDAO;
import entity.Publisher;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        PublisherDAO dao = new PublisherDAO();
        ArrayList<Publisher> l = dao.getAllPublishers();
        for (Publisher publisher : l) {
            System.out.println(publisher.getName());
        }
    }
}
