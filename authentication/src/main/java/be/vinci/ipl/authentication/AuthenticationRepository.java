package be.vinci.ipl.authentication;

import be.vinci.ipl.authentication.model.Credentials;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<Credentials,String> {

}
