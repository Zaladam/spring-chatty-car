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

  public void deleteAllPassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByTripId(id);
    repository.deleteAll(passengers);
  }

  public Trips readTripByPassengerId(long id) {
    Iterable<Passenger> passengers = repository.findByUserId(id);
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

  public void deletePassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByUserId(id);
    repository.deleteAll(passengers);
  }

  public void addPassengerToTrip(long userId, long tripId) {
    Passenger newPassenger = new Passenger();
    newPassenger.setStatus("pending");
    newPassenger.setTripId((int) tripId);
    newPassenger.setUserId((int) userId);

    repository.save(newPassenger);
  }

  public String readStatusFromPassenger(long userId, long tripId) {

    return repository.findByTripIdAndUserId(userId,tripId).getStatus();
  }

  public void updatePassengerStatus(long tripId, long userId, String status) {

    Passenger passenger = repository.findByTripIdAndUserId(tripId,userId);
    passenger.setStatus(status);
    repository.save(passenger);
  }

  public void deletePassengerFromTrip(long tripId, long userId) {
    Passenger passenger = repository.findByTripIdAndUserId(tripId,userId);
    repository.delete(passenger);
  }
}
