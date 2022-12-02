package be.vinci.ipl.gateway.service;

import be.vinci.ipl.gateway.data.AuthentificationProxy;
import be.vinci.ipl.gateway.data.NotificationProxy;
import be.vinci.ipl.gateway.data.PassengerProxy;
import be.vinci.ipl.gateway.data.TripsProxy;
import be.vinci.ipl.gateway.data.UserProxy;
import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewTrip;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.Notification;
import be.vinci.ipl.gateway.models.Trip;
import be.vinci.ipl.gateway.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.util.annotation.Nullable;

@Service
public class GatewayService {
private final AuthentificationProxy authentificationProxy;
private final UserProxy userProxy;
private final NotificationProxy notificationProxy;
private final TripsProxy tripsProxy;
private final PassengerProxy passengerProxy;

public GatewayService(AuthentificationProxy authentificationProxy,
    UserProxy userProxy, NotificationProxy notificationProxy, TripsProxy tripsProxy,
    PassengerProxy passengerProxy){
  this.authentificationProxy = authentificationProxy;
  this.userProxy = userProxy;
  this.notificationProxy = notificationProxy;

  this.tripsProxy = tripsProxy;
  this.passengerProxy = passengerProxy;
}

  /**
   * Connects user with credentials
   * @param credentials The credentials
   * @return The JWT token, or error 404 if the user in request is not correct
   */
public String connect(Credentials credentials){
    return authentificationProxy.connect(credentials);
}

public String verify(String token){
  return authentificationProxy.verify(token);
}

public User createUser(NewUser newUser){
  User userCreated = userProxy.createUser(newUser);
  authentificationProxy.createCredentials(newUser.getEmail(),newUser.toCredentials());
  return userCreated;
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


//delete User
  public Iterable<Trip> getListOfTripOfDriver(int driverId){
    return tripsProxy.getListOfTripOfDriver(driverId);
  }

  public Iterable<Trip> getListOfTripsOfPassenger(int user_id){
  return passengerProxy.getListOfTripsOfPassenger(user_id);
  }

public Iterable<Notification> readAllNotificationsOfAUser(int idUser){
  return notificationProxy.readAllNotificationsOfAUser(idUser);
}

public void deleteAllNotificationsOfAUser(int idUser){
  notificationProxy.deleteAllNotificationsOfAUser(idUser);
}

public Trip createTrip(NewTrip newTrip){
  return tripsProxy.createTrip(newTrip);
}

public Iterable<Trip> getListOfTrips(@Nullable String departureDate, @Nullable long originLat,
    long originLon, @Nullable long destinationLat, @Nullable long destinationLon){
  return tripsProxy.getListOfTrips(departureDate,originLat,originLon,destinationLat,destinationLon);
}





}
