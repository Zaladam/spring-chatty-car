package be.vinci.ipl.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class NewUser {
  @Id
  private String email;
  private String firstName;
  private String lastName;
  private String password;

  public User toUser(){return new User(0,email,firstName,lastName);}
  public User toUser(int id){return new User(id,email,firstName,lastName);}
}
