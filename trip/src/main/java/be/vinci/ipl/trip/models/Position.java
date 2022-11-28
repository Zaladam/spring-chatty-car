package be.vinci.ipl.trip.models;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
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

  private long longitude;
  private long latitude;

  public Position(long latitude, long longitude){
    this.latitude = latitude;
    this.longitude = longitude;
  }

 public double calculeDistance(Position destination){
    return 0;
 }

}
