package com.kearny.puissance4.util;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.function.Consumer;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Runner {
  public static void run(Class clazz) {
    run(clazz, new VertxOptions().setClustered(false));
  }

  private static void run(Class clazz, VertxOptions options) {
    run(clazz.getPackage().getName().replace(".", "/"), clazz.getName(), options);
  }

  private static void run(String exampleDir, String verticleID, VertxOptions options) {
    // Smart cwd detection
    System.setProperty("vertx.cwd", exampleDir);
    Consumer<Vertx> runner = vertx -> {
      try {
        vertx.deployVerticle(verticleID);
      } catch (Throwable t) {
        t.printStackTrace();
      }
    };

    Vertx vertx = Vertx.vertx(options);
    runner.accept(vertx);
  }
}
