package be.vinci.ipl.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUser {
  private String email;
  private String firstname;
  private String lastname;
  private String password;

  public User toUser(){return new User(0,email, firstname, lastname);}
  public User toUser(int id){return new User(id,email, firstname, lastname);}
}
