package be.vinci.ipl.gateway.models;

import java.time.LocalDate;
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
public class NewTrip {
  private Position origin;
  private Position destination;
  private LocalDate departureDate;
  private int driverId;
  private int availableSeating;

}
