package froop.rest.resource;

import lombok.Data;

@Data
public class SampleBean {
  private Integer id;
  private String name;

  // for JSON parse of JAX-RS
  public SampleBean() {
  }

  public SampleBean(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
