package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name="notifications")
public interface NotificationProxy {

  @GetMapping("/notifications/{userId}")
  Iterable<Notification> readAllNotificationsOfAUser(@PathVariable int userId);

  @DeleteMapping("/notifications/{userId}")
  void deleteAllNotificationsOfAUser(@PathVariable int userId);



}
