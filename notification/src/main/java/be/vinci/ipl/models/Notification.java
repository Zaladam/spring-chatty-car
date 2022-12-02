package be.vinci.ipl.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="notifications")
public class Notification {
  @Id
  @Column(name = "id_notification")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idNotification;
  @Column(name="user_id")
  private int userId;
  @Column(name="trip_id")
  private int tripId;
  @DateTimeFormat(iso= ISO.DATE)
  private LocalDate date;
  @Column(name="notification_text")
  private String notificationText;




}
