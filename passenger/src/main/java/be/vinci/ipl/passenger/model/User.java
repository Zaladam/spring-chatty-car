package be.vinci.ipl.passenger.model;

import javax.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
  private int id;
  private String email;
  private String firstname;
  private String lastname;
}
