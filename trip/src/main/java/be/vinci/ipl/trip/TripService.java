package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TripService {

  private final TripRepository repository;

  public TripService(TripRepository repository) {
    this.repository = repository;
  }

  public Trip saveTrip(Trip trip) {
    return repository.save(trip);
  }

  public List<Trip> getAllTrips() {
    return (List<Trip>) repository.findAll();
  }

  public List<Trip> getAllTripsSameOrigin(Position origin) {
    return repository.findAllByOrigin(origin);
  }

  public List<Trip> getAllTripsSameOriginAndSameDestination(Position origin, Position destination) {
    return repository.findAllByOriginAndDestination(origin, destination);
  }

  public List<Trip> getAllTripsSameDepartureDate(LocalDate departureDate) {
    return repository.findAllByDepartureDate(departureDate);
  }

  public Trip getTripById(int id) {
    return repository.findByTripId(id);
  }

  public Integer deleteTripById(int id) {
    return repository.deleteByTripId(id);
  }

  public List<Trip> getListOfTripUserIsDriver(int userId) {
    return repository.findAllByDriverId(userId);
  }

  public boolean deleteTripsByDriverId(int driverId) {
    return repository.deleteAllByDriverId(driverId);
  }



}