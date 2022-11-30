package be.vinci.ipl.users;

import be.vinci.ipl.users.model.User;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
  private final UsersRepository repository;

  public UsersService(UsersRepository repository) {
    this.repository = repository;
  }

  public boolean createOne(User user){
    if (repository.existsById(user.getEmail())) return false;
    repository.save(user);
    return true;
  }

  public User findByEmail(String email){
    return repository.findByEmail(email);
  }

  public User findById(String id ){
    return repository.findById(id).orElse(null);
  }
}
