package be.vinci.ipl.trip.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "trips")
public class Trip {

  @Id
  @Column(name = "trip_id")
  @JsonProperty("trip_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer tripId;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "origin_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "origin_longitude")),
  })
  @JsonProperty("origin")
  private Position origin;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "destination_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "destination_longitude")),
  })
  @JsonProperty("destination")
  private Position destination;

  @Column(name = "driver_id")
  @JsonProperty("driver_id")
  private Integer driverId;
  @Column(name = "available_seating")
  @JsonProperty("available_seating")
  private Integer availableSeating;
  @Column(name = "departure_date")
  @JsonProperty("departure_date")
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate departureDate;
}
