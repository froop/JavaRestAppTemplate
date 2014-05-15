package froop.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("samples")
public class SampleResource {

  @GET
  @Path("text")
  public String getText() {
    return "dummy text";
  }

  @GET
  @Produces(APPLICATION_JSON)
  public List<SampleBean> getList() {
    return Arrays.asList(new SampleBean("1"), new SampleBean("2"));
  }

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public SampleBean getItem(@PathParam("id") String id) {
    return new SampleBean(id);
  }
}
