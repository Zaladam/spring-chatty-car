package be.vinci.ipl.users.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;
  private String email;
  private String firstname;
  private String lastname;
}
