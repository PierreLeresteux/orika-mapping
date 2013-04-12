import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pleresteux
 */
public class MasterMapperFactory<A, B> {

  private final Class<A>     entityClass;
  private final Class<B>     dtoClass;
  private MapperFactory simpleMapperFactory;
  private ClassMapBuilder<A,B> classMapBuilder;

  public MasterMapperFactory(Class<A> entityClass, Class<B> dtoClass) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
    simpleMapperFactory = new DefaultMapperFactory.Builder().build();
    classMapBuilder = simpleMapperFactory.classMap(entityClass, dtoClass).byDefault();
  }

  public void addField(String entityFieldName, String dtoFieldName){
    classMapBuilder.field(entityFieldName,dtoFieldName);
  }

  public MapperFacade getFacade(){
    classMapBuilder.register();
     return simpleMapperFactory.getMapperFacade();
  }

  public A convertDtoToEntity(Object dto){
    return getFacade().map(dto, entityClass);
  }

  public B convertEntityToDto(Object entity) {
    return getFacade().map(entity, dtoClass);
  }

  public List<B> convertEntitiesToDtos(List<A> entities) {
    if (entities == null)
      return null;
    List<B> returnList = new ArrayList<B>();
    for(A entity : entities){
      returnList.add(convertEntityToDto(entity));
    }
    return returnList;
  }

  public List<A> convertDtosToEntities(List<B> dtos) {
    if (dtos == null)
      return null;
    List<A> returnList = new ArrayList<A>();
    for(B dto : dtos){
      returnList.add(convertDtoToEntity(dto));
    }
    return returnList;
  }
}
