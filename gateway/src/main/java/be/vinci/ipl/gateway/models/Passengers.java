package be.vinci.ipl.gateway.models;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Passengers {

  private List<User> pending;
  private List<User> accepted;
  private List<User> refused;

  public Passengers() {
    this.pending = new ArrayList<>();
    this.accepted = new ArrayList<>();
    this.refused = new ArrayList<>();
  }
}
