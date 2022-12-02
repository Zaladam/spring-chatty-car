package be.vinci.ipl.trip.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NoIdTrip {

 @JsonProperty("origin")
 private Position origin;
 @JsonProperty("destination")
 private Position destination;
 @JsonProperty("departure")
 @DateTimeFormat(iso = ISO.DATE)
 private LocalDate departureDate;
 @JsonProperty("driver_id")
 private Integer driverId;
 @JsonProperty("available_seating")
 private Integer availableSeating;

 public Trip toTrip(){
  return new Trip(0, origin, destination,  driverId, availableSeating, departureDate);
 }

}
