package froop.rest.resource;

import lombok.Data;

@Data
@SuppressWarnings("UnusedDeclaration")
public class SampleBean {
  private Integer id;
  private String name;

  public static SampleBean of(Integer id, String name) {
    return new SampleBean(id, name);
  }

  // for JSON parse of JAX-RS
  public SampleBean() {
  }

  public SampleBean(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
