package be.vinci.ipl.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripApplication {

  public static void main(String[] args) {
    SpringApplication.run(TripApplication.class, args);
  }

}

/*
 * @embeded pour le trip
 * @embedable pour la position
 * type LocalDate pour les dates
 * */