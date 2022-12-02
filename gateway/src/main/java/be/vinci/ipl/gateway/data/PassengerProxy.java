package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name="notifications")
public interface PassengerProxy {

  @GetMapping("/passengers/{user_id}")
  Iterable<Trip> getListOfTripsOfPassenger(@PathVariable int user_id);

}
