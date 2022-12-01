package be.vinci.ipl.trip;

import be.vinci.ipl.trip.models.Position;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name="calculator")
public interface CalculatorProxy {

 @GetMapping("/{origin}/{destination}")
  Double getDistance(@PathVariable("origin") Position origin,
     @PathVariable("destination") Position destination);

}
