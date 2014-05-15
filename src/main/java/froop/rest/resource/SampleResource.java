package froop.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("sample")
public class SampleResource {

  @GET
  public String getDummy() {
    return "dummy";
  }
}
