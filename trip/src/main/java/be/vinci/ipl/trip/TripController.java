package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.NoIdTrip;
import be.vinci.ipl.trip.models.Position;
import be.vinci.ipl.trip.models.Trip;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
      @RequestParam(required = false) String departureDate,
      @RequestParam(required = false) Long originLat,
      @RequestParam(required = false) Long originLon,
      @RequestParam(required = false) Long destinationLat,
      @RequestParam(required = false) Long destinationLon) {
    LocalDate parsedDepartureDate = LocalDate.parse(departureDate);
    Position origin;
    Position destination;
    List<Trip> trips;
    if (originLat != null && originLon != null) {
      origin = new Position(originLat, originLon);
    } else {
      origin = null;
    }
    if (destinationLat != null && destinationLon != null) {
      destination = new Position(destinationLat, destinationLon);
    } else {
      destination = null;
    }
    if (parsedDepartureDate != null) {
      // select tout les trips qui ont lieu à cette date
      trips = (List<Trip>) service.getAllByDepartureDate(parsedDepartureDate);
    } else {
      trips = (List<Trip>) service.getAll();
    }

    if (origin != null && destination == null) {
      //select tout les trips et garder que les 20 plus proches de l'origine
      trips = trips.stream().sorted((x, y) -> compareDistance(x.getOrigin(), y.getOrigin(), origin)).toList();
    } else if (origin == null && destination != null) {
      // select tout les trips et garder que les 20 plus proches de la destination
      trips = trips.stream().sorted((x, y) -> compareDistance(x.getDestination(), y.getDestination(), destination)).toList();
    } else {
      // select tout les trips qui ont la même origin et destination et ne garder que les 20 dont la somme des distances est la plus petite
      trips = trips.stream().sorted((x, y) -> compareDistanceSumm(x, y, origin, destination)).toList();
    }
    return ResponseEntity.status(200).body(trips.subList(0, 19));
  }

  public int compareDistance(Position firstOrigin, Position secundOrigin, Position destination){
    double distanceOrigin = calculatorProxy.getDistance(firstOrigin, destination);
    double distanceDestination = calculatorProxy.getDistance(secundOrigin, destination);
    return Double.compare(distanceOrigin, distanceDestination);
  }

  public int compareDistanceSumm(Trip firstTrip, Trip secundTrip, Position origin, Position destination){
    double firstDistanceOrigin = calculatorProxy.getDistance(firstTrip.getOrigin(), origin);
    double firstDistanceDestination = calculatorProxy.getDistance(firstTrip.getDestination(), destination);
    double secundDistanceOrigin = calculatorProxy.getDistance(secundTrip.getOrigin(), origin);
    double secundDistanceDestination = calculatorProxy.getDistance(secundTrip.getDestination(), destination);
    double firstDistanceTotale = firstDistanceDestination + firstDistanceOrigin;
    double secundDistanceTotale = secundDistanceDestination + secundDistanceOrigin;
    return Double.compare(firstDistanceTotale, secundDistanceTotale);
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