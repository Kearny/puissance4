package com.kearny.puissance4;

import com.google.gson.Gson;
import com.kearny.puissance4.util.Runner;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class VertxServer extends AbstractVerticle {

    public static void main(String[] args) {
        Runner.run(VertxServer.class);
    }

    @Override
    public void start() throws Exception {
        Gson gson = new Gson();
        Game game = new Game();
        game.initializeGrid();

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            routingContext.response()
            .putHeader("Access-Control-Allow-Origin", "*")
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(gson.toJson(game.getGrid()));
        });

        vertx.createHttpServer().requestHandler(router).listen(8085);
    }
}