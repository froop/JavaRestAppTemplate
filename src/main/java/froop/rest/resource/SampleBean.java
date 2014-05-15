package froop.rest.resource;

import lombok.Data;

@Data
public class SampleBean {
  private Integer id;
  private String name;

  public SampleBean(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
