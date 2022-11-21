package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Trip;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {
  private final TripService service;

  public TripController(TripService service){
    this.service = service;
  }

  @GetMapping
  public List<Trip> GetListofTrips(){
    return null;
  }

}