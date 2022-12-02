package be.vinci.ipl.calculator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import be.vinci.ipl.calculator.models.Position;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

  public double calculateDistance(Position origin, Position destination) {
    return sqrt(pow((destination.getLongitude() - origin.getLongitude()), 2) +
        pow((destination.getLatitude() - origin.getLatitude()), 2));
  }

}
