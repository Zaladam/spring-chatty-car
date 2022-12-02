package be.vinci.ipl.passenger;

import be.vinci.ipl.passenger.model.Passenger;
import be.vinci.ipl.passenger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersRepository extends CrudRepository<Passenger,String> {

  Iterable<Passenger> findByTripId(int id);

  Iterable<Passenger> findByUserId(int id);

  Passenger findByTripIdAndUserId(int userId, int tripId);
}
