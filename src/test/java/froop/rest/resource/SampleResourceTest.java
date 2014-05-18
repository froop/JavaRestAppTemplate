package froop.rest.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;
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
    GenericType<List<SampleBean>> listType = new GenericType<List<SampleBean>>() {};
    List res = target("samples").register(JacksonFeature.class).request(APPLICATION_JSON).get(listType);

    assertThat(res, is(Arrays.asList(new SampleBean(1, "name1"), new SampleBean(2, "name2"))));
 }

  @Test
  public void testGetItem() throws Exception {
    SampleBean res = target("samples/1").register(JacksonFeature.class).request(APPLICATION_JSON).get(SampleBean.class);

    assertThat(res, is(new SampleBean(1, "name1")));
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