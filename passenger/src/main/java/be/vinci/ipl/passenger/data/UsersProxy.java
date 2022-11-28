package be.vinci.ipl.passenger.data;

import be.vinci.ipl.passenger.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {

  @GetMapping("/users/{user_id}")
  User readOne(@PathVariable int user_id);
}
