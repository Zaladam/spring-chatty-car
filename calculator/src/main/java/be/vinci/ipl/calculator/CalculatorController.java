package be.vinci.ipl.calculator;

import be.vinci.ipl.calculator.models.Position;
import jakarta.ws.rs.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

  private final CalculatorService service = new CalculatorService();


  /**
   * Calcul de la distance simplifié, ne prenant pas en compte la courbure de la terre Source de la
   * formule : <a href="https://fr.wikihow.com/calculer-la-distance-entre-deux-points">...</a>
   **/
  @GetMapping("/{origin}/{destination}")
  public double getDistance(@PathVariable("origin") Position origin,
      @PathVariable("destination") Position destination) {
    return service.calculateDistance(origin, destination);
  }

}
