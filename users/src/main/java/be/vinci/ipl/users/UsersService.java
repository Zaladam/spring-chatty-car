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
  public User createOne(NewUser newUser) {
    if (repository.findByEmail(newUser.getEmail()) != null) {
      return null;
    }
    repository.save(newUser.toUser());
    return repository.findByEmail(newUser.getEmail());
  }

  /**
   * Reads a user by his email
   * @param email email of the user
   * @return The user found
   */
  public User findByEmail(String email) {
    return repository.findByEmail(email);
  }

  /**
   * Reads a user by his id
   * @param id Pseudo id the user
   * @return The user found, or null if the user couldn't be found
   */
  public User findById(int id) {
    return repository.findById(id).orElse(null);
  }
  /**
   * Updates a user
   * @param user User to update
   * @return True if the user could be updated, false if the user couldn't be found
   */
  public boolean updateOne(User user) {
    if (!repository.existsById(user.getId())) {
      return false;
    }
    repository.save(user);
    return true;
  }

  /**
   * Deletes a user
   * @param id id of the user
   * @return True if the user could be deleted, false if the user couldn't be found
   */
  public boolean deleteOne(int id) {
    if (!repository.existsById(id)) {
      return false;
    }
    repository.deleteById(id);
    return true;
  }
}
