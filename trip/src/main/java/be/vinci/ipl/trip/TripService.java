package be.vinci.ipl.trip;

import org.springframework.stereotype.Service;

@Service
public class TripService {

  private final TripRepository repository;

  public TripService(TripRepository repository) {
    this.repository = repository;
  }

}