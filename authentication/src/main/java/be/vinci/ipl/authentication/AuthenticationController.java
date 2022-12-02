package be.vinci.ipl.authentication;

import be.vinci.ipl.authentication.model.Credentials;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

public class AuthenticationController {

  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/auth/connect")
  public String connect(@RequestBody Credentials credentials) {
    if (credentials.getEmail()==null || !Pattern.compile("^(.+)@(\\\\S+)$")
        .matcher(credentials.getEmail()).matches()
        || credentials.getPassword()==null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    String token = service.connect(credentials);
    if (token == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    return token;
  }

  @PostMapping("/auth/verify")
  public String verify(@RequestBody String token) {
    String email = service.verify(token);
    if (email == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    return email;
  }

  @PostMapping("/auth")
  public ResponseEntity<Boolean> createCredentials(
      @RequestBody Credentials credentials) {
    if (credentials.getEmail()==null || !Pattern.compile("^(.+)@(\\\\S+)$")
        .matcher(credentials.getEmail()).matches()
        || credentials.getPassword()==null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean created = service.createCredentials(credentials);
    if (!created) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
  }

  @PutMapping("/auth/{email}")
  public ResponseEntity<Void> updateOne(@PathVariable String email,
      @RequestBody Credentials credentials) {
    if (credentials.getEmail()==null || !Pattern.compile("^(.+)@(\\\\S+)$")
        .matcher(credentials.getEmail()).matches()
        || credentials.getPassword()==null || !email.equals(credentials.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean updated = service.updateCredentials(credentials);
    if (!updated) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/auth/{email}")
  public ResponseEntity<Void> delete(@PathVariable String email,
      @RequestBody Credentials credentials) {
    if (credentials.getEmail()==null || !Pattern.compile("^(.+)@(\\\\S+)$")
        .matcher(credentials.getEmail()).matches()
        || credentials.getPassword()==null || !email.equals(credentials.getEmail())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean deleted = service.deleteCredentials(credentials);
    if(!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
