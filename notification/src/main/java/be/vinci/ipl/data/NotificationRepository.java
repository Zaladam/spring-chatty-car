package be.vinci.ipl.data;

import be.vinci.ipl.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Integer> {


  Iterable<Notification> findAllByUserId(int idUser);

  void deleteAllByUserId(int idUser);

}
