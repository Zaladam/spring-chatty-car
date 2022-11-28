package be.vinci.ipl.passenger.data;

import be.vinci.ipl.passenger.model.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "trips")
public interface TripProxy {
  @GetMapping("/trips/{trip_id}")
  Trip readOne(@PathVariable int trip_id);
}
