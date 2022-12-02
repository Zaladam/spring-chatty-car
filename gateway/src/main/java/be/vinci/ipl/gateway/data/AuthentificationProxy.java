package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.Credentials;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name="authentication")
public interface AuthentificationProxy {
  @PostMapping("/auth/connect")
  String connect(@RequestBody Credentials credentials);

  @PostMapping("/auth/verify")
  String verify(@RequestBody String token);


  @PostMapping("/auth/{email}")
  void createCredentials(@PathVariable String email, @RequestBody Credentials credentials);

  @PutMapping("/auth/{email}")
  void updateCredentials(@PathVariable String email, @RequestBody Credentials credentials);




}
