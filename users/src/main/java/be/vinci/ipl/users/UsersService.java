package be.vinci.ipl.users;

import be.vinci.ipl.users.data.AuthenticationProxy;
import be.vinci.ipl.users.model.Credentials;
import be.vinci.ipl.users.model.NewUser;
import be.vinci.ipl.users.model.User;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
  private final UsersRepository repository;
  private final AuthenticationProxy authenticationProxy;

  public UsersService(UsersRepository repository, AuthenticationProxy authenticationProxy) {
    this.repository = repository;
    this.authenticationProxy = authenticationProxy;
  }

  public boolean createOne(NewUser newUser){
    if (repository.existsById(newUser.getEmail())) return false;
    authenticationProxy.createOne(newUser.getEmail(), new Credentials(newUser.getEmail(),
        newUser.getPassword()));
    repository.save(newUser.toUser());
    return true;
  }

  public User findByEmail(String email){
    return repository.findByEmail(email);
  }

  public User findById(String id){
    return repository.findById(id).orElse(null);
  }

  public boolean updateOne(User user){
    User userFounded = repository.findById("user.getId()").orElse(null);
    if(userFounded==null) return false;
    userFounded.setEmail(user.getEmail());
    userFounded.setFirstName(user.getFirstName());
    userFounded.setLastName(user.getLastName());
    repository.save(userFounded);
    return true;
  }

  public boolean deleteOne(int id){
    if (!repository.existsById("id")) return false;
    repository.deleteById("id");
    return true;
  }
}
