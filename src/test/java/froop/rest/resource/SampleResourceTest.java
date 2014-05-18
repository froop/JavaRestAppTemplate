package froop.rest.resource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SampleResourceTest extends JerseyTest {

  @Override
  protected Application configure() {
    return new ResourceConfig(SampleResource.class);
  }

  @Test
  public void testGetText() throws Exception {
    WebTarget target = target("samples/text");
    Invocation.Builder request = target.request();
    String res = request.get(String.class);

    assertThat(res, is("dummy text"));
  }
}