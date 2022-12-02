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
        newUser.getFirstname() == null || newUser.getLastname() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User in request is not correct");
    }
    User userCreated = service.createOne(newUser);
    if (userCreated==null) throw new ResponseStatusException(HttpStatus.CONFLICT,"A user already exists with this email");
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

  @GetMapping("/users/{email}") //changer dans le yaml et mettre un path pas une query
  public User readOne(@PathVariable String email) {
    User user = service.findByEmail(email);
    if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No user found with this email");
    return user;
  }

  @GetMapping("/users/{id}")
  public User readOneById(@PathVariable int id) {
    User user = service.findById(id);
    if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this ID");
    return user;
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<Void> updateOne(@PathVariable int id,@RequestBody User user){
    if (user.getEmail() == null  || user.getFirstname() == null ||
        user.getLastname() == null || user.getId()!=id) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User in request is not correct");
    }
    boolean updated = service.updateOne(user);
    if(!updated) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this ID");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteOne(@PathVariable int id){
    boolean deleted = service.deleteOne(id);
    if(!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this ID");
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
