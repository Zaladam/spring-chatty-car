package be.vinci.ipl.passenger.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Trip {

  private int tripId;
  private Position origin;
  private Position destination;
  private LocalDate departureDate;
  private int driverId;
  private int availableSeating;

}