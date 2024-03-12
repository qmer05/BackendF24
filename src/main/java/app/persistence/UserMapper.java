package app.persistence;

import app.entities.User;
import app.exceptions.DatabaseException;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public static User login(Context ctx) throws DatabaseException {

        User user;

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        String sql = "SELECT * FROM public.\"user\" WHERE username = ? and password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, ctx.formParam("username"));
                ps.setString(2, ctx.formParam("password"));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    user = new User(id, username, password, role);
                } else {
                    throw new DatabaseException("User not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Could not establish connection to database");
        }
        return user;
    }
}
