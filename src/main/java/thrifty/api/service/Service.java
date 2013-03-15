package thrifty.api.service;

import java.util.List;

import thrifty.api.model.Entity;

/**
 * A Service sits between the Controller and the
 * DataAccess and allows for any additional logic to be
 * performed before hitting the DataAccess object.
 * 
 * @author emmerich
 *
 */
public interface Service {
	
	/**
	 * Performs logic prior to the creation of an Entity.
	 * @param creation the Entity about to be created.
	 */
	public void create(Entity creation);
	
	/**
	 * Performs logic prior to the reading of an Entity.
	 * @param key the ID of the Entity about to be looked up.
	 */
	public Entity read(Integer key);
	
	/**
	 * Performs logic prior to the updating of an Entity.
	 * @param updated the Entity about to be applied to an existing
	 * Entity.
	 */
	public void update(Entity updated);
	
	/**
	 * Performs logic prior to the deletion of an Entity.
	 * @param key the ID of the entity about to be deleted.
	 */
	public void delete(Integer key);
	
	public List<Entity> list();
}
