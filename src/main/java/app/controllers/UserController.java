package app.controllers;

import app.entities.User;
import app.persistence.UserMapper;
import io.javalin.http.Context;

public class UserController {
    public static void login(Context ctx) {
        try {
            User user = UserMapper.login(ctx);
            ctx.sessionAttribute("currentUser", user);

            ctx.redirect("/welcome");
        } catch (Exception e) {
            ctx.attribute("message", e.getMessage());
        }
    }
}
