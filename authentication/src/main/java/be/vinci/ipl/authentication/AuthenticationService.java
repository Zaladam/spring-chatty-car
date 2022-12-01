package be.vinci.ipl.authentication;

import be.vinci.ipl.authentication.model.Credentials;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final AuthenticationRepository repository;
  private final Algorithm jwtAlgorithm;
  private final JWTVerifier jwtVerifier;

  public AuthenticationService(AuthenticationRepository repository, AuthenticationProperties properties) {
    this.repository = repository;
    this.jwtAlgorithm = Algorithm.HMAC512(properties.getSecret());
    this.jwtVerifier = JWT.require(this.jwtAlgorithm).withIssuer("auth0").build();;
  }

  public String connect(Credentials credentials) {
    Credentials credentialsFound = repository.findById(credentials.getEmail()).orElse(null);
    if (credentialsFound == null) {
      return null;
    }
    if (!BCrypt.checkpw(credentials.getPassword(), credentialsFound.getPassword())) {
      return null;
    }
    return JWT.create().withIssuer("auth0").withClaim("email",credentialsFound.getEmail()).sign(jwtAlgorithm);
  }

  public String verify(String token){
    try {
      String email = jwtVerifier.verify(token).getClaim("email").asString();
      if (!repository.existsById(email)) return null;
      return email;
    } catch (JWTVerificationException e) {
      return null;
    }
  }

  public boolean createCredentials(Credentials credentials){
    if(repository.existsById(credentials.getEmail())) return false;
    credentials.setPassword(BCrypt.hashpw(credentials.getPassword(),BCrypt.gensalt()));
    repository.save(credentials);
    return true;
  }

  public boolean updateCredentials(Credentials credentials){
    Credentials credentialsFounded = repository.findById(credentials.getEmail()).orElse(null);
    if(credentialsFounded==null) return false;
    credentialsFounded.setPassword(BCrypt.hashpw(credentials.getPassword(),BCrypt.gensalt()));
    repository.save(credentialsFounded);
    return true;
  }

  public boolean deleteCredentials(Credentials credentials){
    if(!repository.existsById(credentials.getEmail())) return false;
    repository.deleteById(credentials.getEmail());
    return true;
  }
}
