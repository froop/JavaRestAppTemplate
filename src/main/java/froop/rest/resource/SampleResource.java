package froop.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("samples")
public class SampleResource {

  @GET
  @Path("text")
  public String getText() {
    return "dummy text";
  }

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public SampleBean getItem(@PathParam("id") String id) {
    return new SampleBean(id);
  }
}
