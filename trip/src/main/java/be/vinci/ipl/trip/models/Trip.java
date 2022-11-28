package be.vinci.ipl.trip.models;

import java.time.LocalDate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "trips")
public class Trip {

  // Todo Specify json notation

  @Id
  @Column(name = "trip_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tripId;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "origin_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "origin_longitude")),
  })
  private Position origin;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "latitude", column = @Column(name = "destination_latitude")),
      @AttributeOverride(name = "longitude", column = @Column(name = "destination_longitude")),
  })
  private Position destination;
  @Column(name = "departure_date")
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate departureDate;
  @Column(name = "driver_id")
  private int driverId;
  @Column(name = "available_seating")
  private int availableSeating;

}
