package be.vinci.ipl.users.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
  private String firstName;
  private String lastName;
  private String password;

  public User toUser(){return new User(0,email,firstName,lastName);}
  public User toUser(int id){return new User(id,email,firstName,lastName);}
}
