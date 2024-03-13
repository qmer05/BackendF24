package app.entities;

public class Item {
    private int id;
    private String author;
    private String title;
    private String body;
    private int price;

    public Item(int id, String author, String title, String body, int price) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.price = price;
    }

    public String getTimestamp(){
        return "Here is the time";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", price=" + price +
                '}';
    }
}
