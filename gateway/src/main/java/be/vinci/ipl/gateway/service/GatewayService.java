package be.vinci.ipl.gateway.service;

import be.vinci.ipl.gateway.data.AuthentificationProxy;
import be.vinci.ipl.gateway.data.UserProxy;
import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.User;
import org.springframework.stereotype.Service;

@Service
public class GatewayService {
private final AuthentificationProxy authentificationProxy;
private final UserProxy userProxy;

public GatewayService(AuthentificationProxy authentificationProxy,
    UserProxy userProxy){
  this.authentificationProxy = authentificationProxy;
  this.userProxy = userProxy;
}
public String connect(Credentials credentials){
    return authentificationProxy.connect(credentials);
}

public String verify(String token){
  return authentificationProxy.verify(token);
}

public void createUser(NewUser newUser){
  userProxy.createUser(newUser.getEmail(),newUser.toUser());
  authentificationProxy.createCredentials(newUser.getEmail(),newUser.toCredentials());
}

public User readOneUser(String email){
  return userProxy.readOneUserByEmail(email);
}

public void updateCredentials(Credentials userCrendentials){
  authentificationProxy.updateCredentials(userCrendentials.getEmail(), userCrendentials );
}

public User readUserById(int id){
  return userProxy.readUserById(id);
}

public void updateUser(int id, User user){
  userProxy.updateOneUser(id, user);
}

}
