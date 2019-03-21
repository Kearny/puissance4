package com.kearny.puissance4;

import com.google.gson.Gson;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class VertxServerVerticleTest {

  private static final int port = 8085;
  private Vertx vertx;
  private Gson gson = new Gson();

  @BeforeClass
  public static void initialize() {
  }

  @AfterClass
  public static void shutdown() {
  }

  @Test
  public void main() {
    VertxServerVerticle.main(null);
  }

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions()
      .setConfig(new JsonObject()
        .put("http.port", port)
      );

    // We pass the options as the second parameter of the deployVerticle method.
    vertx.deployVerticle(VertxServerVerticle.class.getName(), options, context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testServer(TestContext context) {
    // This test is asynchronous, so get an async handler to inform the test when we are done.
    final var async = context.async();

    // We create a HTTP client and query our application. When we get the response we check it contains the 'Hello'
    // message. Then, we call the `complete` method on the async handler to declare this async (and here the test) done.
    // Notice that the assertions are made on the 'context' object and are not Junit assert. This ways it manage the
    // async aspect of the test the right way.
    vertx.createHttpClient().getNow(port, "localhost", "/", response -> response.handler(body -> {
      context.assertEquals(
        "[[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]]",
        body.toString()
      );

      async.complete();
    }));

    vertx.createHttpClient().post(port, "localhost", "/")
      .putHeader("content-type", "application/json")
      .handler(response -> {
        context.assertEquals(500, response.statusCode());

        async.complete();
      })
      .end();

    final var json = "{ \"player\": \"PLAYER1\", \"column\": 1 }";
    final var length = Integer.toString(json.length());

    vertx.createHttpClient().post(port, "localhost", "/")
      .putHeader("Content-Type", "application/json")
      .putHeader("Content-Length", length)
      .handler(response -> {
        context.assertEquals(200, response.statusCode());
        context.assertTrue(response.headers().get("content-type").contains("application/json"));

        response.bodyHandler(body -> {
          final var game = gson.fromJson(body.toString(), MoveResult.class);

          context.assertEquals(6, game.grid.length);
        });

        async.complete();
      })
      .write(json)
      .end();
  }
}
