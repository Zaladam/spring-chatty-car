package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
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


  public Trip getTripById(int id) {
    return repository.findByTripId(id);
  }

  public Integer deleteTripById(int id) {
    return repository.deleteByTripId(id);
  }

  public Iterable<Trip> getListOfTripUserIsDriver(int userId) {
    return repository.findAllByDriverId(userId);
  }

  public Iterable<Trip> deleteTripsByDriverId(int driverId) {
    return repository.deleteAllByDriverId(driverId);
  }

  public Iterable<Trip> getAllByDepartureDate(LocalDate departureDate) {
    return repository.findAllByAvailableSeatingGreaterThanAndDepartureDateEquals(0, departureDate);
  }

  public Iterable<Trip> getAll() {
    return repository.findAllByAvailableSeatingGreaterThan(0);
  }

}