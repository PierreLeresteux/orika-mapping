import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.junit.Ignore;
import org.junit.Test;

public class MapperDozerTest {

	@Test
	@Ignore
	public void should_convert_a_person_entity() {
		// Given
		PersonEntity pierre = createPierreEntity();
		PersonEntity faycal = createFaycalEntity();
		Set<PersonEntity> coworkers = new HashSet<PersonEntity>();
		coworkers.add(faycal);
		pierre.setCoworkers(coworkers);
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(new BeanMappingBuilder() {
			
			@Override
			protected void configure() {
				 mapping(PersonEntity.class, PersonDto.class)
				 .fields("password", "confirmpassword")
				 .fields("password", "password");
			}
		});
		// When
		PersonDto pierreDto = mapper.map(pierre, PersonDto.class);
		// Then
		isPersonEntityEqualsToPersonDto(pierre, pierreDto);
	}

	 @Test
	 public void should_convert_a_person_entity_using_MasterMapperFactory() {
	 // Given
	 PersonEntity pierreEntity = createPierreEntity();
	 PersonEntity faycal = createFaycalEntity();
	 Set<PersonEntity> coworkers = new HashSet<PersonEntity>();
	 coworkers.add(faycal);
	 pierreEntity.setCoworkers(coworkers);
	 MasterMapperDozerFactory<PersonEntity, PersonDto> factory = new MasterMapperDozerFactory(PersonEntity.class,PersonDto.class);
	 factory.addField("password", "password");
	 factory.addField("password", "confirmpassword");
	 // When
	 PersonDto pierreDto = factory.convertEntityToDto(pierreEntity);
	 // Then
	 isPersonEntityEqualsToPersonDto(pierreEntity, pierreDto);
	 }
	
	 

	 @Test
	 public void should_convert_a_person_dto_using_MasterMapperFactory() {
	 // Given
	 PersonDto pierreDto = createPierreDto();
	 PersonDto faycal = createFaycalDto();
	 Set<PersonDto> coworkers = new HashSet<PersonDto>();
	 coworkers.add(faycal);
	 pierreDto.setCoworkers(coworkers);
	 MasterMapperDozerFactory<PersonEntity, PersonDto> factory = new MasterMapperDozerFactory(PersonEntity.class,PersonDto.class);
	 factory.addField("password", "password");
	 factory.addField("password", "confirmpassword");
	 // When
	 PersonEntity pierreEntity = factory.convertDtoToEntity(pierreDto);
	 // Then
	 isPersonEntityEqualsToPersonDto(pierreEntity, pierreDto);
	 }
	//
	 @Test
	 public void
	 should_convert_a_list_of_person_entity_using_MasterMapperFactory() {
	 // Given
	 List<PersonEntity> personEntities = new ArrayList<PersonEntity>(2);
	 personEntities.add(createPierreEntity());
	 personEntities.add(createFaycalEntity());
	 MasterMapperDozerFactory<PersonEntity, PersonDto> factory = new MasterMapperDozerFactory(PersonEntity.class,PersonDto.class);
	 factory.addField("password", "password");
	 factory.addField("password", "confirmpassword");
	 // When
	 List<PersonDto> personDtos = factory.convertEntitiesToDtos(personEntities);
	 // Then
	 isPersonEntitiesEqualsToPersonDtos(personEntities, personDtos);
	 }
	
	 @Test
	 public void
	 should_convert_a_list_of_person_dto_using_MasterMapperFactory() {
	 // Given
	 List<PersonDto> personDtos = new ArrayList<PersonDto>(2);
	 personDtos.add(createPierreDto());
	 personDtos.add(createFaycalDto());
	 MasterMapperDozerFactory<PersonEntity, PersonDto> factory = new MasterMapperDozerFactory(PersonEntity.class,PersonDto.class);
	 factory.addField("password", "password");
	 factory.addField("password", "confirmpassword");
	 // When
	 List<PersonEntity> personEntities = factory.convertDtosToEntities(personDtos);
	 // Then
	 isPersonEntitiesEqualsToPersonDtos(personEntities, personDtos);
	 }

	private void isPersonEntityEqualsToPersonDto(PersonEntity entity,
			PersonDto dto) {
		assertThat(entity.getFirstname()).isEqualTo(dto.getFirstname());
		assertThat(entity.getLastname()).isEqualTo(dto.getLastname());
		assertThat(entity.getLogin()).isEqualTo(dto.getLogin());
		assertThat(entity.getEmail()).isEqualTo(dto.getEmail());
		assertThat(entity.getAge()).isEqualTo(dto.getAge());
		assertThat(entity.getPassword()).isEqualTo(dto.getPassword());
		assertThat(entity.getPassword()).isEqualTo(dto.getConfirmpassword());
		isRoleEntityEqualsToRoleDto(entity.getPrimaryRole(),
				dto.getPrimaryRole());
		isRoleEntitiesEqualsToRoleDtos(entity.getSecondaryRoles(),
				dto.getSecondaryRoles());
		if (entity.getCoworkers() != null && dto.getCoworkers() != null) {
			isPersonEntitiesEqualsToPersonDtos(entity.getCoworkers(),
					dto.getCoworkers());
		}
	}

	private void isPersonEntitiesEqualsToPersonDtos(Set<PersonEntity> entities,
			Set<PersonDto> dtos) {
		assertThat(entities).isNotNull();
		assertThat(dtos).isNotNull();
		assertThat(entities).hasSize(dtos.size());
		Iterator<PersonEntity> entityIterator = entities.iterator();
		Iterator<PersonDto> dtoIterator = dtos.iterator();
		while (entityIterator.hasNext()) {
			isPersonEntityEqualsToPersonDto(entityIterator.next(),
					dtoIterator.next());
		}
	}

	private void isRoleEntityEqualsToRoleDto(RoleEntity roleEntity,
			RoleDto roleDto) {
		assertThat(roleEntity.getName()).isEqualTo(roleDto.getName());
	}

	private void isRoleEntitiesEqualsToRoleDtos(List<RoleEntity> roleEntities,
			List<RoleDto> roleDtos) {
		assertThat(roleEntities).isNotNull();
		assertThat(roleDtos).isNotNull();
		assertThat(roleEntities).hasSize(roleDtos.size());
		for (int i = 0; i < roleEntities.size(); i++) {
			isRoleEntityEqualsToRoleDto(roleEntities.get(i), roleDtos.get(i));
		}
	}

	private void isPersonEntitiesEqualsToPersonDtos(
			List<PersonEntity> entities, List<PersonDto> dtos) {
		assertThat(entities).isNotNull();
		assertThat(dtos).isNotNull();
		assertThat(entities).hasSize(dtos.size());
		for (int i = 0; i < entities.size(); i++) {
			isPersonEntityEqualsToPersonDto(entities.get(i), dtos.get(i));
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
