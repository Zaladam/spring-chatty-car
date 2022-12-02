package be.vinci.ipl.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoIdNotification {
  private int userId;
  private int tripId;
  private LocalDate date;
  private String notificationText;

  public Notification toNotification(){
    return new Notification(0,userId,tripId,date,notificationText);
  }

}
