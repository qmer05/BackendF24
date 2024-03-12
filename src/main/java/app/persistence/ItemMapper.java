package app.persistence;

import app.entities.Item;
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

        String sql = "SELECT * FROM item";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt("item_id");
                    String author = rs.getString("author");
                    String title = rs.getString("title");
                    String body = rs.getString("body");
                    int price = rs.getInt("price");
                    items.add(new Item(id, author, title, body, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Could not establish connection to database");
        }
        return items;
    }
}

/*
#ItemMapper
Har til formål at hente data fra databasen og lave en liste af Items ud fra dataen.
Den skal have tilføjet følgende metode:
public static List<Item> getItems(Context ctx)
{
    // fetch data from database (same procedure as we did in UserMapper.login())
    // instead of if(rs.next()) then use a while loop; while(rs.next())
    // inside the while loop, you create a new Item() using data from the db and add it to an arraylist.
    // return the arraylist

 */