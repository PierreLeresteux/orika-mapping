import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * User: pleresteux
 */
public class PersonDto implements Serializable {
  private String         firstname;
  private String         lastname;
  private String         login;
  private String         email;
  private String         password;
  private String         confirmpassword;
  private int            age;
  private RoleDto        primaryRole;
  private List<RoleDto>  secondaryRoles;
  private Set<PersonDto> coworkers;

  public PersonDto() {
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmpassword() {
    return confirmpassword;
  }

  public void setConfirmpassword(String confirmpassword) {
    this.confirmpassword = confirmpassword;
  }

  public RoleDto getPrimaryRole() {
    return primaryRole;
  }

  public void setPrimaryRole(RoleDto primaryRole) {
    this.primaryRole = primaryRole;
  }

  public List<RoleDto> getSecondaryRoles() {
    return secondaryRoles;
  }

  public void setSecondaryRoles(List<RoleDto> secondaryRoles) {
    this.secondaryRoles = secondaryRoles;
  }

  public Set<PersonDto> getCoworkers() {
    return coworkers;
  }

  public void setCoworkers(Set<PersonDto> coworkers) {
    this.coworkers = coworkers;
  }
}
