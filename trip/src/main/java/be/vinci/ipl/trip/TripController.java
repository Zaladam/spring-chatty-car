package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.NoIdTrip;
import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
   * utiliser un RequestParam(required = true | false) au lieu de QueryParam
   **/
//  @GetMapping("/?departure_date&originLat&originLon&destinationLat&destinationLon")
  @GetMapping("/")
  public ResponseEntity<List<Trip>> getListOfTrips(
      @QueryParam("departure_date") String departureDate,
      @QueryParam("originLat") Long originLat, @QueryParam("originLon") Long originLon,
      @QueryParam("destinationLat") Long destinationLat,
      @QueryParam("destinationLon") Long destinationLon) {
    LocalDate parserDepartureDate = LocalDate.parse(departureDate);
    Position origin = new Position(originLat, originLon);
    Position destination = new Position(destinationLat, destinationLon);
    List<Trip> trips;
    if (originLat != 0 && originLon != 0 && destinationLat != 0 && destinationLon != 0) {
      trips = service.getAllTripsSameOriginAndSameDestination(origin, destination);
    } else if (parserDepartureDate != null) {
      trips = service.getAllTripsSameDepartureDate(parserDepartureDate);
    } else {
      trips = service.getAllTrips();
    }

    return ResponseEntity.status(200).body(trips.subList(0, 19));
  }

  @PostMapping("/")
  public ResponseEntity<Trip> createTrip(@RequestBody NoIdTrip trip) {
    if (trip == null
        || trip.getOrigin().getLatitude() == null || trip.getOrigin().getLongitude() == null
        || trip.getDestination().getLatitude() == null
        || trip.getDestination().getLongitude() == null
        || trip.getDepartureDate() == null || trip.getAvailableSeating() == null
        || trip.getAvailableSeating() <= 0
    ) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Trip tripCreated = service.saveTrip(trip.toTrip());
    return ResponseEntity.status(HttpStatus.CREATED).body(tripCreated);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Trip> getTripById(@PathVariable int id) {
    Trip trip = service.getTripById(id);
    if (trip == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.status(200).body(service.getTripById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Integer> deleteTripById(@PathVariable int id) {
    Trip trip = service.getTripById(id);
    if (trip == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.status(201).body(service.deleteTripById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Trip> decreaseAvailableSeating(@PathVariable int id) {
    Trip trip = service.getTripById(id);
    if (trip == null || trip.getAvailableSeating() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    trip.setAvailableSeating(trip.getAvailableSeating() - 1);
    service.saveTrip(trip);
    return ResponseEntity.status(200).body(trip);
  }

  @GetMapping("/{driverId}/driver")
  public ResponseEntity<Iterable<Trip>> getListOfTripOfDriver(@PathVariable int driverId) {
    Iterable<Trip> trips = service.getListOfTripUserIsDriver(driverId);
    return ResponseEntity.status(200).body(trips);
  }

  @DeleteMapping("/{driverId}/driver")
  public ResponseEntity<Iterable<Trip>> deleteTripsByDriverId(@PathVariable int driverId) {
    Iterable<Trip> trips = service.deleteTripsByDriverId(driverId);
    return ResponseEntity.status(200).body(trips);
  }

}