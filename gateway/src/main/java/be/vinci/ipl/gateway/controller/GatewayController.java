package be.vinci.ipl.gateway.controller;

import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.User;
import be.vinci.ipl.gateway.service.GatewayService;
import javax.ws.rs.QueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GatewayController {

  private final GatewayService gatewayService;

  public GatewayController(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }

  @PostMapping("/auth")
   String connect(@RequestBody Credentials credentials){
    return gatewayService.connect(credentials);
  }

@PostMapping("/users") //BadRequest & UNauth.
ResponseEntity<Void> createUser(@RequestBody NewUser newUser){
    gatewayService.createUser(newUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
}

@GetMapping("/users") //a verifier
  User readOneUser(@QueryParam("email") String email){
    if(email==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    return gatewayService.readOneUser(email);
}

@PutMapping("/users")
  void updatePasswordUser(@RequestBody Credentials credentials, @RequestHeader("Authorization") String token ){
    String user= gatewayService.verify(token);
    if(!user.equals(credentials.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    gatewayService.updateCredentials(credentials);
}

@GetMapping("/users/{id}")
  User readUserById(@PathVariable int id, @RequestHeader("Authorization") String token){
  String userEmail = gatewayService.verify(token);
  if (!userEmail.equals(userEmail)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
  return gatewayService.readUserById(id);
}




}
