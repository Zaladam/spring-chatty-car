package be.vinci.ipl.users;

import be.vinci.ipl.users.model.NewUser;
import be.vinci.ipl.users.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {
  private final UsersService service;

  public UsersController(UsersService service) {
    this.service = service;
  }

  @PostMapping("/users")
  public ResponseEntity<User> createOne(@RequestBody NewUser newUser){
    if (newUser.getEmail() == null ||
        newUser.getFirstName() == null || newUser.getLastName() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    User user = service.createOne(newUser);
    if (user == null) throw new ResponseStatusException(HttpStatus.CONFLICT);
    return new ResponseEntity<User>(user,HttpStatus.CREATED);
  }

  @GetMapping("/users/{email}")
  public User readOne(@PathVariable String email) {
    User user = service.findByEmail(email);
    if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return user;
  }

  @GetMapping("/users/{id}")
  public User readOneById(@PathVariable int id) {
    User user = service.findById(id);
    if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return user;
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<Void> updateOne(@PathVariable int id,@RequestBody User user){
    if (user.getEmail() == null  || user.getFirstName() == null || user.getLastName() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean updated = service.updateOne(user);
    if(!updated) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteOne(@PathVariable int id,@RequestBody User user){
    if (user.getEmail() == null  || user.getFirstName() == null || user.getLastName() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean deleted = service.deleteOne(user.getId());
    if(!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
