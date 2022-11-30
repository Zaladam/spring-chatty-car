package be.vinci.ipl.users;

import be.vinci.ipl.users.model.NewUser;
import be.vinci.ipl.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {
  private final UsersService service;

  public UsersController(UsersService service) {
    this.service = service;
  }

  @PostMapping("/users/{email}")
  public ResponseEntity<Void> createOne(@PathVariable String email,@RequestBody NewUser newUser){
    if (newUser.getEmail() == null || !newUser.getEmail().equals(email) ||
        newUser.getFirstName() == null || newUser.getLastName() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    User user = new User();
    user.setEmail(newUser.getEmail());
    user.setFirstName(newUser.getFirstName());
    user.setLastName(newUser.getLastName());
    boolean created = service.createOne(user);
    if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/users/{email}")
  public User readOne(@PathVariable String email) {
    User user = service.findByEmail(email);
    if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return user;
  }


}
