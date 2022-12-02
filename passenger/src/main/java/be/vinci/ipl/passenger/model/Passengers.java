package be.vinci.ipl.passenger.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
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
