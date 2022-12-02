package be.vinci.ipl.passenger.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trips {

  private List<Trip> pending;
  private List<Trip> accepted;
  private List<Trip> refused;

  public Trips() {
    this.pending = new ArrayList<>();
    this.accepted = new ArrayList<>();
    this.refused = new ArrayList<>();
  }
}