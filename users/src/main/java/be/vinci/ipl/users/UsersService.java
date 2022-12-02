package be.vinci.ipl.users;

import be.vinci.ipl.users.model.NewUser;
import be.vinci.ipl.users.model.User;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  private final UsersRepository repository;

  public UsersService(UsersRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a user
   * @param newUser User to create
   * @return true if the user could be created, false if another user exists with this pseudo
   */
  public boolean createOne(NewUser newUser) {
    if (repository.findByEmail(newUser.getEmail()) != null) {
      return false;
    }
    repository.save(newUser.toUser());
    return true;
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public User findById(int id) {
    return repository.findById(id).orElse(null);
  }

  public boolean updateOne(User user) {
    User userFounded = repository.findById(user.getId()).orElse(null);
    if (userFounded == null) {
      return false;
    }
    userFounded.setEmail(user.getEmail());
    userFounded.setFirstname(user.getFirstname());
    userFounded.setLastname(user.getLastname());
    repository.save(userFounded);
    return true;
  }

  public boolean deleteOne(int id) {
    if (!repository.existsById(id)) {
      return false;
    }
    repository.deleteById(id);
    return true;
  }
}
