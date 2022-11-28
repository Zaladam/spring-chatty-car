package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import java.util.List;
import net.bytebuddy.asm.Advice.Local;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {


  List<Trip> findAllByDepartureDateAndAvailableSeatingGreaterThanOrderByDepartureDate(
      LocalDate departureDate, int availableSeating);

  List<Trip> findAllByDepartureDate(LocalDate departureDate);

  List<Trip> findAllByOrigin(Position origin);

  List<Trip> findAllByDestination(Position destination);

  Trip findByTripId(int id);

  boolean deleteByTripId(int id);

  boolean deleteAllByDriverId(int driverId);

  List<Trip> findAllByOriginAndDestination(Position origin, Position destination);

}