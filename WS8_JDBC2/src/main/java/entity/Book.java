package entity;

public class Book {
    private int id;
    private String title;
    private float price;
    private int quantity;
    private Publisher publisher;

    public Book(int id, String title, float price, int quantity, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    
}
