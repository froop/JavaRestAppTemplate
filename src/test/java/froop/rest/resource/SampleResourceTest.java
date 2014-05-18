package froop.rest.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.Iterator;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class SampleResourceTest extends JerseyTest {

  @Override
  protected Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig(SampleResource.class).register(JacksonFeature.class);
  }

  @Test
  public void testGetText() throws Exception {
    String res = target("samples/text").request().get(String.class);

    assertThat(res, is("dummy text"));
  }

  @Test
  public void testGetList() throws Exception {
    JsonArray res = target("samples").request().accept(APPLICATION_JSON).get(JsonArray.class);

    Iterator<JsonValue> it = res.iterator();
    assertItem(it.next().toString(), 1, "name1");
    assertItem(it.next().toString(), 2, "name2");
    assertFalse(it.hasNext());
  }

  @Test
  public void testGetItem() throws Exception {
    String res = target("samples/1").request().accept(APPLICATION_JSON).get(String.class);

    assertItem(res, 1, "name1");
  }

  private void assertItem(String json, int id, String name) {
    assertThat(json, is("{\"id\":" + id + ",\"name\":\"" + name + "\"}"));
  }

  @Test
  public void testCreate() throws Exception {
    SampleBean req = new SampleBean(null, "new name");

    Response res = target("samples").register(JacksonFeature.class).request().post(Entity.entity(req, APPLICATION_JSON));

    assertThat(res.getStatus(), is(204));
  }

  @Test
  public void testUpdate() throws Exception {
    SampleBean req = new SampleBean(1, "name1b");

    Response res = target("samples/1").register(JacksonFeature.class).request().put(Entity.entity(req, APPLICATION_JSON));

    assertThat(res.getStatus(), is(204));
  }

  @Test
  public void testDelete() throws Exception {
    Response res = target("samples/1").register(JacksonFeature.class).request().delete();

    assertThat(res.getStatus(), is(204));
  }
}