import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * User: pleresteux
 */
public class PersonEntity implements Serializable {

  private String           firstname;
  private String           lastname;
  private String           login;
  private String           email;
  private String           password;
  private int              age;
  private RoleEntity       primaryRole;
  private List<RoleEntity> secondaryRoles;
  private Set<PersonEntity> coworkers;

  public PersonEntity() {
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public RoleEntity getPrimaryRole() {
    return primaryRole;
  }

  public void setPrimaryRole(RoleEntity primaryRole) {
    this.primaryRole = primaryRole;
  }

  public List<RoleEntity> getSecondaryRoles() {
    return secondaryRoles;
  }

  public void setSecondaryRoles(List<RoleEntity> secondaryRoles) {
    this.secondaryRoles = secondaryRoles;
  }

  public Set<PersonEntity> getCoworkers() {
    return coworkers;
  }

  public void setCoworkers(Set<PersonEntity> coworkers) {
    this.coworkers = coworkers;
  }
}
