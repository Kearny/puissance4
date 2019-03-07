package com.kearny.puissance4;

import com.kearny.puissance4.util.Runner;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class VertxServer extends AbstractVerticle {

    public static void main(String[] args) {
        Runner.run(VertxServer.class);
    }

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route().handler(routingContext -> {
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}