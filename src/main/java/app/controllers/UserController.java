package app.controllers;

import app.persistence.UserMapper;
import io.javalin.http.Context;

public class UserController {
    public static void login(Context ctx) {
        try {
            UserMapper.login(ctx);
            ctx.redirect("/welcome.html");
        } catch (Exception e) {
            ctx.attribute("message", e.getMessage());
        }
    }
}
