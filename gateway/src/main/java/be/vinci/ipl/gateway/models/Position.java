package be.vinci.ipl.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Position {

  private long longitude;
  private long latitude;

  public double calculeDistance(){
    return 0;
  }

}