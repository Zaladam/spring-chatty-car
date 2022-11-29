package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {

  private final TripService service;

  public TripController(TripService service) {
    this.service = service;
  }


  /**
   * TODO
   * Si on ne spécifie que l'origine, on select tout les trips et on ne garde que les 20 premiers les plus proche de l'origine
   * Si on ne spécifie que la destination, on select tout les trips et on ne garde que les 20 premiers les plus proche de la destination
   * Si on spécifie les deux, on select tout les trips et on ne garde que les 20 premiers dont la somme des 2 distances est la plus petites
   *
   **/
//  @GetMapping("/?departure_date&originLat&originLon&destinationLat&destinationLon")
  @GetMapping("/")
  public List<Trip> GetListofTrips(@QueryParam("departure_date") String departure_date,
      @QueryParam("originLat") Long originLat, @QueryParam("originLon") Long originLon,
      @QueryParam("destinationLat") Long destinationLat,
      @QueryParam("destinationLon") Long destinationLon) {
    LocalDate departureDate = LocalDate.parse(departure_date);
    Position origin = new Position(originLat, originLon);
    Position destination = new Position(destinationLat, destinationLon);
    List<Trip> trips;
    if (originLat != 0 && originLon != 0 && destinationLat != 0 && destinationLon != 0) {
      trips = service.getAllTripsSameOriginAndSameDestination(origin, destination);
//      trips = trips.stream().sorted((x,y)->{
//        double xDistance =x.getOrigin().calculeDistance(x.getDestination());
//        double yDistance = y.getOrigin().calculeDistance(y.getDestination());
//            return Double.compare(xDistance, yDistance);
//          })
//          .toList();
    } else if (departureDate != null) {
      trips = service.getAllTripsSameDepartureDate(departureDate);
    } else {
      trips = service.getAllTrips();
    }

    return trips.subList(0, 19);
  }

  // Todo Create model noIdTrip because before created, there is no id
  @PostMapping("/")
  public Trip createTrip(@RequestBody Trip trip) {
    return service.createTrip(trip);
  }

}