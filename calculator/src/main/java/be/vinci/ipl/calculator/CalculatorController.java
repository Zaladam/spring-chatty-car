package be.vinci.ipl.calculator;

import be.vinci.ipl.calculator.models.Position;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

  private final CalculatorService service = new CalculatorService();


  /**
   * Calcul de la distance simplifié, ne prenant pas en compte la courbure de la terre Source de la
   * formule : <a href="https://fr.wikihow.com/calculer-la-distance-entre-deux-points">...</a>
   **/
  @GetMapping("/{origin}/{destination}")
  public ResponseEntity<Double> getDistance(@PathVariable("origin") Position origin,
      @PathVariable("destination") Position destination) {
    if(origin == null || destination == null) throw new ResponseStatusException(HttpStatusCode.valueOf(400));
    return ResponseEntity.status(200).body(service.calculateDistance(origin, destination));
  }

}
