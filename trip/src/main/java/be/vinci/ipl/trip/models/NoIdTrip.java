package be.vinci.ipl.trip.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NoIdTrip {

 private Position origin;
 private Position destination;
 private LocalDate departureDate;
 private int driverId;
 private int availableSeating;

}
