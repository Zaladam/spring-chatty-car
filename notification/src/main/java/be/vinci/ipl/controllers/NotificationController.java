package be.vinci.ipl.controllers;

import be.vinci.ipl.models.NoIdNotification;
import be.vinci.ipl.models.Notification;
import be.vinci.ipl.service.NotificationService;
import java.time.LocalDate;
import javax.ws.rs.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

  private final NotificationService notificationService;


  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("/{userId}")
  public Iterable<Notification> readAll(@PathVariable int userId){
    return notificationService.findAllNotificationOfUser(userId);
  }

  @DeleteMapping("/{userId}")
  public boolean deleteAll(@PathVariable int userId){
    notificationService.deleteAllNotificationsOfUser(userId);
    return true;
  }

  @PostMapping("")
  public ResponseEntity<Notification> createOne(@RequestBody NoIdNotification notification){
    if(notification.getNotificationText()==null ||
        notification.getTripId()< 0 || notification.getUserId()<0)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    notification.setDate(LocalDate.now());
    Notification notificationCreated = notificationService.createOne(notification);
    return new ResponseEntity<>(notificationCreated,HttpStatus.CREATED);
  }
}
