package be.vinci.ipl.passenger;

import be.vinci.ipl.passenger.model.Passengers;
import be.vinci.ipl.passenger.model.Trips;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PassengersController {
  private final PassengersService service;

  public PassengersController(PassengersService service) {
    this.service = service;
  }

  @GetMapping("/passengers/{trip_id}")
  public ResponseEntity<Passengers> readFromTrip(@PathVariable long trip_id){
    Passengers passengers = service.findPassengersFromTrip(trip_id);
    if(passengers==null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No trip found with this ID");
    return new ResponseEntity<>(passengers,HttpStatus.OK);
  }

  @DeleteMapping("/passengers/{trip_id}")
  public ResponseEntity<Void> deleteAllPassengers(@PathVariable long trip_id){
    boolean response = service.deleteAllPassengersFromTrip(trip_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No trip found with this ID");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/passengers/{user_id}")
  public ResponseEntity<Trips> readPassengerFromTrip(@PathVariable long user_id){
    Trips trips = service.readTripByPassengerId(user_id);
    if(trips==null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with this ID");
    return new ResponseEntity<>(trips,HttpStatus.OK);
  }

  @DeleteMapping("/passengers/{user_id}")
  public ResponseEntity<Void> deletePassengerFromTrip(@PathVariable long user_id){
    boolean response = service.deletePassengersFromTrip(user_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with this ID");
    return new ResponseEntity<>(HttpStatus.OK);

  }

  @PostMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<Void> addPassengerFromTrip(@PathVariable long user_id,@PathVariable long trip_id){
    boolean response = service.addPassengerToTrip(user_id,trip_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<String> readPassengerFromTrip(@PathVariable long user_id,@PathVariable long trip_id){
    String status = service.readStatusFromPassenger(user_id,trip_id);
    if(status == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(status,HttpStatus.OK);
  }

  @PutMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<Void> updatePassengerStatus(@PathVariable long user_id,@PathVariable long trip_id,@RequestParam String status){
    boolean response = service.updatePassengerStatus(trip_id,user_id,status);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(HttpStatus.OK);

  }
  @DeleteMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<Void> deletePassengerFromTrip(@PathVariable long user_id,@PathVariable long trip_id){
    boolean response = service.deletePassengerFromTrip(trip_id,user_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(HttpStatus.OK);

  }
}
