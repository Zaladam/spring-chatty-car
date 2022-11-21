package be.vinci.ipl.trip;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {
  private final TripService service;

  public TripController(TripService service){
    this.service = service;
  }
}