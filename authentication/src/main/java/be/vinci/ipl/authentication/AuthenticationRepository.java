package be.vinci.ipl.authentication;

import be.vinci.ipl.authentication.model.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<Credentials,String> {

}
