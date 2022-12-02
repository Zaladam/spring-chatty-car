package be.vinci.ipl.trip.models;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Embeddable
public class Position {

  private Long longitude;
  private Long latitude;

  public Position(long latitude, long longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

}
