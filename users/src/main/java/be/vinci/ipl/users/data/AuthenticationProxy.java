package be.vinci.ipl.users.data;

import be.vinci.ipl.users.model.Credentials;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name = "videos")
public interface AuthenticationProxy {
  @PostMapping("/auth/{email}")
  ResponseEntity<Void> createOne(@PathVariable String email, Credentials credentials);
}
