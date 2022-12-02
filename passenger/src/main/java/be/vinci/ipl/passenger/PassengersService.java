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

  /**
   * Find passengers From a trip
   * @param id id of the trip
   * @return null if the trip is not found or A custom object Passengers with depending on the status
   *
   */
  public Passengers findPassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByTripId(id);
    if(passengers == null)
      return null;
    Passengers result = new Passengers();

    for(Passenger passenger : passengers) {
      User user = usersProxy.readOneById(passenger.getUserId());
      switch (passenger.getStatus()) {
        case "pending" -> result.getPending().add(user);
        case "accepted" -> result.getAccepted().add(user);
        case "refused" -> result.getRefused().add(user);
      }
    }
    return result;
  }

  /**
   * Delete all passengers form a trip
   * @param id id of the trip
   * @return False if the trip is not found or True
   */
  public boolean deleteAllPassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByTripId(id);
    if(passengers == null)
      return false;
    repository.deleteAll(passengers);
    return true;
  }

  /**
   * Read trips from user ID
   * @param id id of the passenger
   * @return Trips or null if the user is not found
   */
  public Trips readTripByPassengerId(long id) {
    Iterable<Passenger> passengers = repository.findByUserId(id);
    if (passengers == null)
      return null;
    Trips result = new Trips();
    for (Passenger passenger : passengers ) {
      Trip trip = tripProxy.getTripById(passenger.getTripId());
      switch (passenger.getStatus()) {
        case "pending" -> result.getPending().add(trip);
        case "accepted" -> result.getAccepted().add(trip);
        case "refused" -> result.getRefused().add(trip);
      }
    }
    return result;
  }

  /**
   * Delete All trips where the user is a passenger
   * @param id of the user
   * @return true if done or false if user not found
   */
  public boolean deletePassengersFromTrip(long id) {
    Iterable<Passenger> passengers = repository.findByUserId(id);
    if(passengers == null)
      return false;
    repository.deleteAll(passengers);
    return true;
  }

  /**
   * Add user as passenger to a trip with pending status
   * @param userId id of the user
   * @param tripId id of the trip
   * @return true if done or false if user or trip is not found
   */
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

  /**
   * Read the status of a passenger
   * @param userId id of the user
   * @param tripId id of the trip
   * @return The string of the status or null
   */
  public String readStatusFromPassenger(long userId, long tripId) {

    return repository.findByTripIdAndUserId(userId,tripId).getStatus();
  }

  /**
   * Update the status of a passenger for a trip
   * @param tripId id of the trip
   * @param userId id of the user
   * @param status status of the passenger
   * @return true if done or false if user or trip is not found
   */
  public boolean updatePassengerStatus(long tripId, long userId, String status) {

    Passenger passenger = repository.findByTripIdAndUserId(tripId,userId);
    if(passenger == null)
      return false;
    passenger.setStatus(status);
    repository.save(passenger);
    return true;
  }

  /**
   * delete passenger of a trip
   * @param tripId id of the trip
   * @param userId id of the user
   * @return true if done or false if user or trip is not found
   */
  public boolean deletePassengerFromTrip(long tripId, long userId) {
    Passenger passenger = repository.findByTripIdAndUserId(tripId,userId);
    if(passenger == null)
      return false;
    repository.delete(passenger);
    return true;
  }
}
