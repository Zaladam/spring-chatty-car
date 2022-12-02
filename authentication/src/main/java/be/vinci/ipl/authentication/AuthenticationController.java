package be.vinci.ipl.authentication;

import be.vinci.ipl.authentication.model.Credentials;
import be.vinci.ipl.authentication.model.InsecureCredentials;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthenticationController {

  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/auth/connect")
  public String connect(@RequestBody InsecureCredentials insecureCredentials) {
    if (insecureCredentials.getEmail() == null
        || insecureCredentials.getPassword() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Credentials in request are not correct");
    }
    String token = service.connect(insecureCredentials);
    if (token == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password");
    }
    return token;
  }

  @PostMapping("/auth/verify")
  public String verify(@RequestBody String token) {
    String email = service.verify(token);
    if (email == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"ajouter msg erreur");//
    }
    return email;
  }

  @PostMapping("/auth")
  public ResponseEntity<Boolean> createCredentials(
      @RequestBody InsecureCredentials insecureCredentials) {
    if (insecureCredentials.getEmail()==null || !Pattern.compile("^(.+)@(\\\\S+)$")
        .matcher(insecureCredentials.getEmail()).matches()
        || insecureCredentials.getPassword()==null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean created = service.createCredentials(insecureCredentials);
    if (!created) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Credentials already exists");
    }
    return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
  }

  @PutMapping("/auth/{email}")
  public ResponseEntity<Void> updateOne(@PathVariable String email,
      @RequestBody InsecureCredentials insecureCredentials) {
    if (insecureCredentials.getEmail() == null
        || insecureCredentials.getPassword() == null || !email.equals(insecureCredentials.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Credentials in request are not correct");
    }
    boolean updated = service.updateCredentials(insecureCredentials);
    if (!updated) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this email");
    }
    else return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/auth/{email}")
  public ResponseEntity<Void> delete(@PathVariable String email) {
    boolean deleted = service.deleteCredentials(email);
    if (!deleted) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with this email");
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
