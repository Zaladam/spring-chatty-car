package be.vinci.ipl.passenger.model;


import javax.persistence.Column;
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
@Entity(name = "passengers")
public class Passenger {
  @Id
  private int id;
  @Column(name = "trip_id")
  private int tripId;
  @Column(name = "user_id")
  private int userId;
  private String status;
}
