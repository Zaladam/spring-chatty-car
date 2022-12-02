package be.vinci.ipl.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsecureCredentials {

  private String email;
  private String password;

  public Credentials toCredentials(String hashedPassword){
    return new Credentials(email, hashedPassword);
  }


}
