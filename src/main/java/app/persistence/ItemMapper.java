package app.persistence;

import app.entities.Item;
import app.entities.User;
import app.exceptions.DatabaseException;

import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    public static List<Item> getItems(Context context) throws DatabaseException {
        List<Item> items = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        String sql = "SELECT * FROM public.item";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("item_id");
                    String author = rs.getString("author");
                    String title = rs.getString("title");
                    String body = rs.getString("body");
                    int price = rs.getInt("price");
                    items.add(new Item(id, author, title, body, price));
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Could not get all items from database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Could not establish connection to database");
        }
        return items;
    }

    public static void createNewItem(Context ctx) {
        String sql = "INSERT INTO public.item (author, title, body, price) VALUES (?, ?, ?, ?)";

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                User user = ctx.sessionAttribute("currentUser");
                String author = user.getUsername();
                String title = ctx.formParam("title");
                String body = ctx.formParam("body");
                int price = Integer.parseInt(ctx.formParam("price"));

                ps.setString(1,author);
                ps.setString(2,title);
                ps.setString(3,body);
                ps.setInt(4,price);

                ps.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}