package be.vinci.ipl.users;

import be.vinci.ipl.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
  User findByEmail(String email);

}
