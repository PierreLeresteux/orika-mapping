import static java.util.Arrays.asList;

import java.util.ArrayList;

import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;

/**
 * User: pleresteux
 */
public class MapperBenchmark extends SimpleBenchmark {

  @Param({"1","10","50","100"}) private int listSize;
  private ArrayList<PersonEntity>                           personEntities;
  private ArrayList<PersonDto>                              personDtos;
  private MasterMapperFactory<PersonEntity, PersonDto>      orikaFactory;
  private MasterMapperDozerFactory<PersonEntity, PersonDto> dozerFactory;

  @Override
  protected void setUp() throws Exception {
    orikaFactory = new MasterMapperFactory<PersonEntity, PersonDto>(PersonEntity.class, PersonDto.class);
    dozerFactory = new MasterMapperDozerFactory<PersonEntity, PersonDto>(PersonEntity.class, PersonDto.class);
    dozerFactory.addField("password", "password");
    dozerFactory.addField("password", "confirmpassword");
    personEntities = new ArrayList<PersonEntity>();
    personDtos = new ArrayList<PersonDto>();
    for (int i = 0; i < 50; i++) {
      personEntities.add(createPierreEntity());
      personEntities.add(createFaycalEntity());
      personDtos.add(createPierreDto());
      personDtos.add(createFaycalDto());
    }
  }

  public void timeConvertEntityWithDozer(int reps) {
    for (int i = 0; i < reps; i++) {
      dozerFactory.convertEntitiesToDtos(personEntities.subList(0,listSize));
    }
  }

  public void timeConvertDtoWithDozer(int reps) {
    for (int i = 0; i < reps; i++) {
      dozerFactory.convertDtosToEntities(personDtos.subList(0,listSize));
    }
  }

  public void timeConvertEntityWithOrika(int reps) {
    for (int i = 0; i < reps; i++) {
      orikaFactory.convertEntitiesToDtos(personEntities.subList(0,listSize));
    }
  }

  public void timeConvertDtoWithOrika(int reps) {
    for (int i = 0; i < reps; i++) {
      orikaFactory.convertDtosToEntities(personDtos.subList(0,listSize));
    }
  }

  private PersonEntity createPierreEntity() {
    PersonEntity pierre = new PersonEntity();
    pierre.setFirstname("Pierre");
    pierre.setLastname("Leresteux");
    pierre.setLogin("pleresteux");
    pierre.setEmail("pierre.leresteux@masternaut.com");
    pierre.setPassword("victoriasecret");
    pierre.setAge(30);
    RoleEntity primaryRoleEntity = new RoleEntity();
    primaryRoleEntity.setName("DEV");
    primaryRoleEntity.setCode(001);
    pierre.setPrimaryRole(primaryRoleEntity);
    RoleEntity secondaryRoleEntity1 = new RoleEntity();
    secondaryRoleEntity1.setName("BACKOFFICE");
    secondaryRoleEntity1.setCode(006);
    RoleEntity secondaryRoleEntity2 = new RoleEntity();
    secondaryRoleEntity2.setName("COLLECTOR");
    secondaryRoleEntity2.setCode(016);
    pierre.setSecondaryRoles(asList(secondaryRoleEntity1,
        secondaryRoleEntity2));
    return pierre;
  }

  private PersonDto createPierreDto() {
    PersonDto pierre = new PersonDto();
    pierre.setFirstname("Pierre");
    pierre.setLastname("Leresteux");
    pierre.setLogin("pleresteux");
    pierre.setEmail("pierre.leresteux@masternaut.com");
    pierre.setAge(30);
    RoleDto primaryRoleDto = new RoleDto();
    primaryRoleDto.setName("DEV");
    pierre.setPrimaryRole(primaryRoleDto);
    RoleDto secondaryRoleEntity1 = new RoleDto();
    secondaryRoleEntity1.setName("BACKOFFICE");
    RoleDto secondaryRoleEntity2 = new RoleDto();
    secondaryRoleEntity2.setName("COLLECTOR");
    pierre.setSecondaryRoles(asList(secondaryRoleEntity1,
        secondaryRoleEntity2));
    return pierre;
  }

  private PersonEntity createFaycalEntity() {
    PersonEntity faycal = new PersonEntity();
    faycal.setFirstname("Faycal");
    faycal.setLastname("Brihi");
    faycal.setLogin("fbrihi");
    faycal.setEmail("faycal.Brihi@masternaut.com");
    faycal.setPassword("nepasdormir");
    faycal.setAge(27);
    RoleEntity primaryRoleEntity = new RoleEntity();
    primaryRoleEntity.setName("DEV");
    primaryRoleEntity.setCode(001);
    faycal.setPrimaryRole(primaryRoleEntity);
    RoleEntity secondaryRoleEntity1 = new RoleEntity();
    secondaryRoleEntity1.setName("BACKOFFICE");
    secondaryRoleEntity1.setCode(006);
    RoleEntity secondaryRoleEntity2 = new RoleEntity();
    secondaryRoleEntity2.setName("COLLECTOR");
    secondaryRoleEntity2.setCode(016);
    RoleEntity secondaryRoleEntity3 = new RoleEntity();
    secondaryRoleEntity3.setName("UI MM");
    secondaryRoleEntity3.setCode(021);
    faycal.setSecondaryRoles(asList(secondaryRoleEntity1,
        secondaryRoleEntity2, secondaryRoleEntity3));
    return faycal;
  }

  private PersonDto createFaycalDto() {
    PersonDto faycal = new PersonDto();
    faycal.setFirstname("Faycal");
    faycal.setLastname("Brihi");
    faycal.setLogin("fbrihi");
    faycal.setEmail("faycal.Brihi@masternaut.com");
    faycal.setAge(27);
    RoleDto primaryRoleDto = new RoleDto();
    primaryRoleDto.setName("DEV");
    faycal.setPrimaryRole(primaryRoleDto);
    RoleDto secondaryRoleEntity1 = new RoleDto();
    secondaryRoleEntity1.setName("BACKOFFICE");
    RoleDto secondaryRoleEntity2 = new RoleDto();
    secondaryRoleEntity2.setName("COLLECTOR");
    RoleDto secondaryRoleEntity3 = new RoleDto();
    secondaryRoleEntity3.setName("UI MM");
    faycal.setSecondaryRoles(asList(secondaryRoleEntity1,
        secondaryRoleEntity2, secondaryRoleEntity3));
    return faycal;
  }
}
