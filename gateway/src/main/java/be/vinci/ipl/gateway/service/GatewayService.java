package be.vinci.ipl.gateway.service;

import be.vinci.ipl.gateway.data.AuthentificationProxy;
import be.vinci.ipl.gateway.data.NotificationProxy;
import be.vinci.ipl.gateway.data.UserProxy;
import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.Notification;
import be.vinci.ipl.gateway.models.User;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {
private final AuthentificationProxy authentificationProxy;
private final UserProxy userProxy;
private final NotificationProxy notificationProxy;

public GatewayService(AuthentificationProxy authentificationProxy,
    UserProxy userProxy, NotificationProxy notificationProxy){
  this.authentificationProxy = authentificationProxy;
  this.userProxy = userProxy;
  this.notificationProxy = notificationProxy;
}
public String connect(Credentials credentials){
    return authentificationProxy.connect(credentials);
}

public String verify(String token){
  return authentificationProxy.verify(token);
}

public void createUser(NewUser newUser){
  userProxy.createUser(newUser.getEmail(),newUser.toUser());
  authentificationProxy.createCredentials(newUser.getEmail(),newUser.toCredentials());
}

public User readUserByEmail(String email){
  return userProxy.readUserByEmail(email);
}

public void updateCredentials(Credentials userCrendentials){
  authentificationProxy.updateCredentials(userCrendentials.getEmail(), userCrendentials );
}

public User readUserById(int id){
  return userProxy.readUserById(id);
}

public void updateUser(int id, User user){
  userProxy.updateOneUser(id, user);
}

public Iterable<Notification> readAllNotificationsOfAUser(int idUser){
  return notificationProxy.readAllNotificationsOfAUser(idUser);
}

public void deleteAllNotificationsOfAUser(int idUser){
  notificationProxy.deleteAllNotificationsOfAUser(idUser);
}

}
