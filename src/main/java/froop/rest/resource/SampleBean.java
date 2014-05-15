package froop.rest.resource;

import lombok.Data;

@Data
public class SampleBean {
  private String id;

  public SampleBean(String id) {
    this.id = id;
  }
}
