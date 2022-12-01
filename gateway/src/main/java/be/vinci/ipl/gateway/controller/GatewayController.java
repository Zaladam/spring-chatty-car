package be.vinci.ipl.gateway.controller;

import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.Notification;
import be.vinci.ipl.gateway.models.User;
import be.vinci.ipl.gateway.service.GatewayService;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GatewayController {

  private final GatewayService gatewayService;

  public GatewayController(GatewayService gatewayService) {
    this.gatewayService = gatewayService;
  }

  @PostMapping("/auth")
  String connect(@RequestBody Credentials credentials) {
    return gatewayService.connect(credentials);
  }

  @PostMapping("/users")
  ResponseEntity<Void> createUser(@RequestBody NewUser newUser) {
    gatewayService.createUser(newUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/users") //a verifier
  @ResponseBody
  User readUserByEmail(@RequestParam(required = true) String email) {
    return gatewayService.readUserByEmail(email);
  }

  @PutMapping("/users")
  void updatePasswordUser(@RequestBody Credentials credentials,
      @RequestHeader("Authorization") String token) {
    String userEmail = gatewayService.verify(token);
    if (!userEmail.equals(credentials.getEmail())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,
          "Not identified as the corresponding user");
    }
    gatewayService.updateCredentials(credentials);
  }

  @GetMapping("/users/{id}")
  User readUserById(@PathVariable int id, @RequestHeader("Authorization") String token) {
    gatewayService.verify(token);
    return gatewayService.readUserById(id);
  }

  @PutMapping("/users/{id}")
  void updateUser(@PathVariable int id, @RequestBody User user,@RequestHeader("Authorization") String token ){
    if(user.getIdUser()!=id)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User in request is not correct");
    String userEmail = gatewayService.verify(token);
    if(gatewayService.readUserByEmail(userEmail).getIdUser()!=id)
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Not identified as the corresponding user");
    gatewayService.updateUser(id,user);
  }

  //user/{id}/driver

  ///user/{id}/passenger

  @GetMapping("/user/{id}/notifications")
  Iterable<Notification> readAllNotificationsOfAUser(@PathVariable int idUser, @RequestHeader("Authorization") String token){

    gatewayService.readUserById(idUser);//404 notfound!!
    String userEmail = gatewayService.verify(token);//401
    if(gatewayService.readUserByEmail(userEmail).getIdUser()!=idUser)
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Not identified as the corresponding user");//403
    return gatewayService.readAllNotificationsOfAUser(idUser);
  }

  @DeleteMapping("/user/{id}/notifications")
  void deleteAllNotificationsOfAUser(@PathVariable int idUser,  @RequestHeader("Authorization") String token){
    gatewayService.readUserById(idUser);//404 notfound!!
    String userEmail = gatewayService.verify(token);//401
    if(gatewayService.readUserByEmail(userEmail).getIdUser()!=idUser)
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Not identified as the corresponding user");//403

    gatewayService.deleteAllNotificationsOfAUser(idUser);

  }




}
