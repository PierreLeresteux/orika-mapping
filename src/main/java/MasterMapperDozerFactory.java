import java.util.ArrayList;
import java.util.List;

import ma.glasnost.orika.MapperFacade;

import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;

public class MasterMapperDozerFactory<A, B> {

	private final Class<A> entityClass;
	private final Class<B> dtoClass;
	private DozerBeanMapper mapper;
	private List<String> entityFieldsA;
	private List<String> entityFieldsB;

	

	public MasterMapperDozerFactory(final Class<A> entityClass, final Class<B> dtoClass) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
		entityFieldsA = new ArrayList<String>();
		entityFieldsB = new ArrayList<String>();
		mapper = null;
	}

	public void addField(final String entityFieldNameA, final String entityFieldNameB) {
		entityFieldsA.add(entityFieldNameA);
		entityFieldsB.add(entityFieldNameB);
	}

	public DozerBeanMapper getMapper(){
		if(mapper == null){
			mapper = new DozerBeanMapper();
			mapper.addMapping(new BeanMappingBuilder() {
				
				@Override
				protected void configure() {
					 TypeMappingBuilder mapping = mapping(entityClass, dtoClass);
					 for (int i = 0; i<entityFieldsA.size();i++){
						 mapping = mapping.fields(entityFieldsA.get(i), entityFieldsB.get(i));
					 }
					
				}
			});
			
		}
		return mapper;
	}

	public A convertDtoToEntity(Object dto) {
		return getMapper().map(dto, entityClass);
	}

	public B convertEntityToDto(Object entity) {
		return getMapper().map(entity, dtoClass);
	}

	public List<B> convertEntitiesToDtos(List<A> entities) {
		if (entities == null)
			return null;
		List<B> returnList = new ArrayList<B>();
		for (A entity : entities) {
			returnList.add(convertEntityToDto(entity));
		}
		return returnList;
	}

	public List<A> convertDtosToEntities(List<B> dtos) {
		if (dtos == null)
			return null;
		List<A> returnList = new ArrayList<A>();
		for (B dto : dtos) {
			returnList.add(convertDtoToEntity(dto));
		}
		return returnList;
	}

}
