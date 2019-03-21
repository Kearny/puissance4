package com.kearny.puissance4;

import com.google.gson.Gson;
import com.kearny.puissance4.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;

public class VertxServerVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Runner.run(VertxServerVerticle.class);
  }

  @Override
  public void start(Future<Void> future) {
    Gson gson = new Gson();
    Game game = new Game();
    game.initializeGrid();

    Router router = Router.router(vertx);

    Set<String> allowedHeaders = new HashSet<>();
    allowedHeaders.add("x-requested-with");
    allowedHeaders.add("Access-Control-Allow-Origin");
    allowedHeaders.add("origin");
    allowedHeaders.add("Content-Type");
    allowedHeaders.add("accept");
    allowedHeaders.add("X-PINGARUNER");

    Set<HttpMethod> allowedMethods = new HashSet<>();
    allowedMethods.add(HttpMethod.GET);
    allowedMethods.add(HttpMethod.POST);
    allowedMethods.add(HttpMethod.OPTIONS);

    router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));
    router.route().handler(BodyHandler.create());

    router.route(HttpMethod.GET, "/").handler(routingContext -> {
      routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
        .end(gson.toJson(game.getGrid()));
    });

    router.post("/").handler(routingContext -> {
      try {
        JsonObject responseBody = routingContext.getBodyAsJson();

        PlayerEnum player = PlayerEnum.valueOf(responseBody.getString("player"));
        Integer column = responseBody.getInteger("column");

        routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
          .end(gson.toJson((game.placeMoveOnGrid(player, column))));

      } catch (Exception e) {
        throw new IllegalArgumentException("Can't read body.");
      }
    });

    vertx.createHttpServer().requestHandler(router).listen(8085, result -> {
      if (result.succeeded()) {
        future.complete();
      } else {
        future.fail(result.cause());
      }
    });
  }
}
