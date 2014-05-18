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
    List<SampleBean> res = target("samples").register(JacksonFeature.class)
        .request(APPLICATION_JSON).get(new SampleListType());

    assertThat(res, is(Arrays.asList(SampleBean.of(1, "name1"), SampleBean.of(2, "name2"))));
 }

  private static class SampleListType extends GenericType<List<SampleBean>> {
  }

  @Test
  public void testGetItem() throws Exception {
    SampleBean res = target("samples/1").register(JacksonFeature.class)
        .request(APPLICATION_JSON).get(SampleBean.class);

    assertThat(res, is(SampleBean.of(1, "name1")));
  }

  @Test
  public void testCreate() throws Exception {
    SampleBean req = SampleBean.of(null, "new name");

    Response res = target("samples").register(JacksonFeature.class)
        .request().post(Entity.entity(req, APPLICATION_JSON));

    assertThat(res.getStatus(), is(204));
  }

  @Test
  public void testUpdate() throws Exception {
    SampleBean req = SampleBean.of(1, "name1b");

    Response res = target("samples/1").register(JacksonFeature.class)
        .request().put(Entity.entity(req, APPLICATION_JSON));

    assertThat(res.getStatus(), is(204));
  }

  @Test
  public void testDelete() throws Exception {
    Response res = target("samples/1").register(JacksonFeature.class)
        .request().delete();

    assertThat(res.getStatus(), is(204));
  }
}