package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {


  Iterable<Trip> findAllByDepartureDate(LocalDate departureDate);

  Iterable<Trip> findAllByOrigin(Position origin);


  Trip findByTripId(int id);

  Integer deleteByTripId(int id);

  Iterable<Trip> deleteAllByDriverId(int driverId);

  Iterable<Trip> findAllByOriginAndDestination(Position origin, Position destination);

  Iterable<Trip> findAllByDriverId(int driverId);

  Iterable<Trip> findAllByAvailableSeatingGreaterThanAndDepartureDateEquals(
      Integer availableSeating,
      LocalDate departureDate);

  Iterable<Trip> findAllByAvailableSeatingGreaterThan(Integer availableSeating);

}