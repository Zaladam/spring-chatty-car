package be.vinci.ipl.passenger;


import be.vinci.ipl.passenger.data.TripProxy;
import be.vinci.ipl.passenger.data.UsersProxy;
import be.vinci.ipl.passenger.model.Passenger;
import be.vinci.ipl.passenger.model.Passengers;
import be.vinci.ipl.passenger.model.Trip;
import be.vinci.ipl.passenger.model.Trips;
import be.vinci.ipl.passenger.model.User;
import org.springframework.stereotype.Service;

@Service
public class PassengersService {

  private final PassengersRepository repository;

  private final UsersProxy usersProxy;
  private final TripProxy tripProxy;

  public PassengersService(PassengersRepository repository, UsersProxy usersProxy,
      TripProxy tripProxy){
    this.repository = repository;
    this.usersProxy = usersProxy;
    this.tripProxy = tripProxy;
  }

  public Passengers findPassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByTripId(id);
    if(passengers == null)
      return null;
    Passengers result = new Passengers();

    for(Passenger passenger : passengers) {
      User user = usersProxy.readOne(passenger.getUserId());
      switch (passenger.getStatus()) {
        case "pending" -> result.getPending().add(user);
        case "accepted" -> result.getAccepted().add(user);
        case "refused" -> result.getRefused().add(user);
      }
    }
    return result;
  }

  public boolean deleteAllPassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByTripId(id);
    if(passengers == null)
      return false;
    repository.deleteAll(passengers);
    return true;
  }

  public Trips readTripByPassengerId(long id) {
    Iterable<Passenger> passengers = repository.findByUserId(id);
    if (passengers == null)
      return null;
    Trips result = new Trips();
    for (Passenger passenger : passengers ) {
      Trip trip = tripProxy.readOne(passenger.getTripId());
      switch (passenger.getStatus()) {
        case "pending" -> result.getPending().add(trip);
        case "accepted" -> result.getAccepted().add(trip);
        case "refused" -> result.getRefused().add(trip);
      }
    }
    return result;
  }

  public boolean deletePassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByUserId(id);
    if(passengers == null)
      return false;
    repository.deleteAll(passengers);
    return true;
  }

  public boolean addPassengerToTrip(long userId, long tripId) {
    Passenger newPassenger = new Passenger();
    Passenger verification = repository.findByTripIdAndUserId(userId,tripId);
    if(verification==null)
      return false;
    newPassenger.setStatus("pending");
    newPassenger.setTripId((int) tripId);
    newPassenger.setUserId((int) userId);

    repository.save(newPassenger);
    return true;
  }

  public String readStatusFromPassenger(long userId, long tripId) {

    return repository.findByTripIdAndUserId(userId,tripId).getStatus();
  }

  public boolean updatePassengerStatus(long tripId, long userId, String status) {

    Passenger passenger = repository.findByTripIdAndUserId(tripId,userId);
    if(passenger == null)
      return false;
    passenger.setStatus(status);
    repository.save(passenger);
    return true;
  }

  public boolean deletePassengerFromTrip(long tripId, long userId) {
    Passenger passenger = repository.findByTripIdAndUserId(tripId,userId);
    if(passenger == null)
      return false;
    repository.delete(passenger);
    return true;
  }
}
