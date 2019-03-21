package com.kearny.puissance4;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.*;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.ServerSocket;

@RunWith(VertxUnitRunner.class)
public class VertxServerVerticleTest {

  private Vertx vertx;
  private static final int PORT = 8085;

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
  public void setUp(TestContext context) throws IOException {
    vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions()
      .setConfig(new JsonObject()
        .put("http.port", PORT)
      );

    // We pass the options as the second parameter of the deployVerticle method.
    vertx.deployVerticle(VertxServerVerticle.class.getName(), options, context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testMyApplication(TestContext context) {
    // This test is asynchronous, so get an async handler to inform the test when we are done.
    final Async async = context.async();

    // We create a HTTP client and query our application. When we get the response we check it contains the 'Hello'
    // message. Then, we call the `complete` method on the async handler to declare this async (and here the test) done.
    // Notice that the assertions are made on the 'context' object and are not Junit assert. This ways it manage the
    // async aspect of the test the right way.
    vertx.createHttpClient().getNow(PORT, "localhost", "/", response -> response.handler(body -> {
      context.assertEquals(
        "[[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0]]",
        body.toString()
      );
      async.complete();
    }));
  }
}
