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
public class Notification {
  //à verifier
  private int idNotification;
  private int userId;
  private int tripId;
  private LocalDate date;
  private String notificationText;




}