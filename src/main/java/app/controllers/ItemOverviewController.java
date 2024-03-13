package app.controllers;

import app.entities.Item;
import app.persistence.ItemMapper;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class ItemOverviewController {
    public static List<Item> loadItems(Context ctx) {

        List<Item> items = new ArrayList<>();
        try {
            items = ItemMapper.getItems(ctx);
        } catch (Exception e) {
            ctx.attribute("message", e.getMessage());
        }
        return items;
    }
}

