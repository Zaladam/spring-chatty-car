package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name="users")
public interface UserProxy {

  @PostMapping("/users")
  User createUser(@RequestBody NewUser newUser);

  @GetMapping("/users/{email}")
  User readUserByEmail(@PathVariable String email);

  @GetMapping("/users/{id}")
  User readUserById(@PathVariable int id);

  @PutMapping("/users/{id}")
  void updateOneUser(@PathVariable int id, @RequestBody User user);

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable int id);



}
