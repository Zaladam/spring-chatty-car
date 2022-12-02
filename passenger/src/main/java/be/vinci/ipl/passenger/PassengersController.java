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


  /**
   * Get the passengers of a trip
   * @param trip_id id of the trip
   * @return ResponseEntity of a http response OK and with a Custom Object Passengers containing 3 lists of passengers by status
   */
  @GetMapping("/passengers/{trip_id}")
  public ResponseEntity<Passengers> readFromTrip(@PathVariable int trip_id){
    Passengers passengers = service.findPassengersFromTrip(trip_id);
    if(passengers==null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No trip found with this ID");
    return new ResponseEntity<>(passengers,HttpStatus.OK);
  }

  /**
   * Delete all the passengers from a trip
   * @param trip_id id of the trip
   * @return ResponseEntity with a http response OK
   */
  @DeleteMapping("/passengers/{trip_id}")
  public ResponseEntity<Void> deleteAllPassengers(@PathVariable int trip_id){
    boolean response = service.deleteAllPassengersFromTrip(trip_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No trip found with this ID");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Get All trips where the passenger is in
   * @param user_id id of the passenger
   * @return ResponseEntity of a http response OK and with a Custom Object Trips containing 3 lists of passengers by status
   */
  @GetMapping("/passengers/{user_id}")
  public ResponseEntity<Trips> readPassengerFromTrip(@PathVariable int user_id){
    Trips trips = service.readTripByPassengerId(user_id);
    if(trips==null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with this ID");
    return new ResponseEntity<>(trips,HttpStatus.OK);
  }

  /**
   * Delete All trips where the user is in
   * @param user_id id of the passenger
   * @return ResponseEntity of a http response OK
   */

  @DeleteMapping("/passengers/{user_id}")
  public ResponseEntity<Void> deleteAllTripsFromPassengerID(@PathVariable int user_id){
    boolean response = service.deletePassengersFromTrip(user_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with this ID");
    return new ResponseEntity<>(HttpStatus.OK);

  }

  /**
   * Add user as passenger to a trip with pending status
   * @param user_id id of the user
   * @param trip_id id of the trip
   * @return ResponseEntity of a http response OK
   */
  @PostMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<Void> addPassengerFromTrip(@PathVariable int user_id,@PathVariable int trip_id){
    boolean response = service.addPassengerToTrip(user_id,trip_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Get the status of the passenger for a certain trip
   * @param user_id id of the user
   * @param trip_id id of the trip
   * @return ResponseEntity of a http response OK and a String with the status
   */
  @GetMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<String> readPassengerFromTrip(@PathVariable int user_id,@PathVariable int trip_id){
    String status = service.readStatusFromPassenger(user_id,trip_id);
    if(status == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(status,HttpStatus.OK);
  }

  /**
   * Update the passenger status to accepted or refused
   * @param user_id id of the user
   * @param trip_id id of the trip
   * @param status Accepted or Refused
   * @return ResponseEntity of a http response OK
   */
  @PutMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<Void> updatePassengerStatus(@PathVariable int user_id,@PathVariable int trip_id,@RequestParam String status){
    boolean response = service.updatePassengerStatus(trip_id,user_id,status);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(HttpStatus.OK);

  }

  /**
   * Remove the user from passenger of a trip
   * @param user_id id of the user
   * @param trip_id id of the trip
   * @return ResponseEntity of a http response OK
   */
  @DeleteMapping("/passengers/{trip_id}/{user_id}")
  public ResponseEntity<Void> deleteAllTripsFromPassengerID(@PathVariable int user_id,@PathVariable int trip_id){
    boolean response = service.deletePassengerFromTrip(trip_id,user_id);
    if(!response)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Trip or user not found");
    return new ResponseEntity<>(HttpStatus.OK);

  }
}
