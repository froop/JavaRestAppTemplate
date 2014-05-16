package froop.rest.resource;

import javax.ws.rs.*;

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
    return Arrays.asList(new SampleBean(1, "name1"), new SampleBean(2, "name2"));
  }

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public SampleBean getItem(@PathParam("id") Integer id) {
    return new SampleBean(id, "name" + id);
  }

  @POST
  @Consumes(APPLICATION_JSON)
  public void create(SampleBean item) {
    System.out.println("POST: " + item);
  }

  @PUT
  @Path("{id}")
  @Consumes(APPLICATION_JSON)
  public void update(@PathParam("id") Integer id, SampleBean item) {
    System.out.println("PUT: id=" + id + ", " + item);
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Integer id) {
    System.out.println("DELETE: id=" + id);
  }
}
