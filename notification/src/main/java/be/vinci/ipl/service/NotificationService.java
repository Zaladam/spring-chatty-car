package be.vinci.ipl.service;

import be.vinci.ipl.data.NotificationRepository;
import be.vinci.ipl.models.NoIdNotification;
import be.vinci.ipl.models.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;


  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  /**
   * Reads all notifications of a user
   * @param userId the id of the user
   * @return The list of all notifications of a user
   */
  public Iterable<Notification> findAllNotificationOfUser(int userId){
    return notificationRepository.findAllByUserId(userId);
  }

  public void deleteAllNotificationsOfUser(int userId){
    notificationRepository.deleteAllByUserId(userId);
  }

  public Notification createOne(NoIdNotification noIdNotification){
   return notificationRepository.save(noIdNotification.toNotification());

  }




}
