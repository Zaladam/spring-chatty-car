package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.NewTrip;
import be.vinci.ipl.gateway.models.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(name="trips")
public interface TripsProxy {

  @PostMapping("/trips")
  Trip createTrip(@RequestBody NewTrip newTrip);

  @GetMapping("/trips")
  Iterable<Trip> getListOfTrips(@RequestParam(name = "departure_date" ,required = false) String departureDate,
      @RequestParam(name = "originLat", required = false) long originLat,
      @RequestParam(name = "originLon") long originLon,
      @RequestParam(name = "destinationLat", required = false ) long destinationLat,
      @RequestParam(name = "destinationLon" ,required = false ) long destinationLon);
}
