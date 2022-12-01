package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.NoIdTrip;
import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
public class TripController {

  private final TripService service;
  private final CalculatorProxy calculatorProxy;

  public TripController(TripService service, CalculatorProxy calculatorProxy) {
    this.service = service;
    this.calculatorProxy = calculatorProxy;
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
  public List<Trip> getListOfTrips(@QueryParam("departure_date") String departure_date,
      @QueryParam("originLat") Long originLat, @QueryParam("originLon") Long originLon,
      @QueryParam("destinationLat") Long destinationLat,
      @QueryParam("destinationLon") Long destinationLon) {
    LocalDate departureDate = LocalDate.parse(departure_date);
    Position origin = new Position(originLat, originLon);
    Position destination = new Position(destinationLat, destinationLon);
    List<Trip> trips;
    if (originLat != 0 && originLon != 0 && destinationLat != 0 && destinationLon != 0) {
      trips = service.getAllTripsSameOriginAndSameDestination(origin, destination);
    } else if (departureDate != null) {
      trips = service.getAllTripsSameDepartureDate(departureDate);
    } else {
      trips = service.getAllTrips();
    }

    return trips.subList(0, 19);
  }

  @PostMapping("/")
  public Trip createTrip(@RequestBody NoIdTrip trip) {
    return service.createTrip(trip.toTrip());
  }

 @GetMapping("/{id}")
 public Trip getTripById(@PathVariable int id){
    return service.getTripById(id);
 }

 @DeleteMapping("/{id}")
  public boolean deleteTripById(@PathVariable int id){
    return service.deleteTripById(id);
 }

 @GetMapping("/{driverId}/driver")
  public List<Trip> getListOfTripOfDriver(@PathVariable int driverId){
    return service.getListOfTripUserIsDriver(driverId);
 }

 @DeleteMapping("/{driverId}/driver")
  public boolean deleteTripsByDriverId(@PathVariable int driverId){
    return service.deleteTripsByDriverId(driverId);
 }

}