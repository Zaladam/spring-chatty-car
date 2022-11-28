package be.vinci.ipl.passenger;

import be.vinci.ipl.passenger.model.Passengers;
import be.vinci.ipl.passenger.model.Trips;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengersController {
  private final PassengersService service;

  public PassengersController(PassengersService service) {
    this.service = service;
  }

  @GetMapping("/passengers/{trip_id}")
  public Passengers readFromTrip(@PathVariable long trip_id){

    return service.findPassengersFromTrip(trip_id);
  }

  @DeleteMapping("/passengers/{trip_id}")
  public void deleteAllPassengers(@PathVariable long trip_id){

    service.deleteAllPassengersFromTrip(trip_id);
  }

  @GetMapping("/passengers/{user_id}")
  public Trips readPassengerFromTrip(@PathVariable long user_id){

    return service.readTripByPassengerId(user_id);
  }

  @DeleteMapping("/passengers/{user_id}")
  public void deletePassengerFromTrip(@PathVariable long user_id){

    service.deletePassengersFromTrip(user_id);
  }

  @PostMapping("/passengers/{trip_id}/{user_id}")
  public void addPassengerFromTrip(@PathVariable long user_id,@PathVariable long trip_id){

    service.addPassengerToTrip(user_id,trip_id);

  }

  @GetMapping("/passengers/{trip_id}/{user_id}")
  public String readPassengerFromTrip(@PathVariable long user_id,@PathVariable long trip_id){

    return service.readStatusFromPassenger(user_id,trip_id);
  }

  @PutMapping("/passengers/{trip_id}/{user_id}")
  public void updatePassengerStatus(@PathVariable long user_id,@PathVariable long trip_id,@RequestParam String status){

    service.updatePassengerStatus(trip_id,user_id,status);
  }
  @DeleteMapping("/passengers/{trip_id}/{user_id}")
  public void deletePassengerFromTrip(@PathVariable long user_id,@PathVariable long trip_id){

    service.deletePassengerFromTrip(trip_id,user_id);
  }
}
