package app;

import app.config.ThymeleafConfig;
import app.controllers.ItemOverviewController;
import app.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing
        app.get("/", ctx -> ctx.redirect("index.html"));
        app.get("/index.html", ctx -> ctx.render("index.html"));

        app.post("/login", ctx -> {
            UserController.login(ctx);
        });

        app.get("/welcome.html", ctx -> ctx.render("welcome.html"));

        app.get("/welcome", ctx -> {
            var items = ItemOverviewController.loadItems(ctx);
            ctx.render("/welcome.html", Map.of("items", items));
        });


    }
}