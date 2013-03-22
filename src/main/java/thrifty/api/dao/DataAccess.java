package thrifty.api.dao;

import java.util.List;

import thrifty.api.model.Entity;

/**
 * DataAccess provides the interface used to interact
 * with the persistence unit.
 * 
 * @author emmerich
 */
public interface DataAccess {
	
	/**
	 * Creates a new Entity.
	 * @param creation the Entity to create.
	 */
	public void create(Entity creation);
	
	/**
	 * Reads an Entity from the DB.
	 * @param key the ID of the Entity to read.
	 */
	public Entity read(Integer key);
	
	/**
	 * Updates an existing entity with the given newer
	 * Entity.
	 * @param updated the Entity with the updated fields to 
	 * be persisted
	 */
	public Entity update(Entity updated);
	
	/**
	 * Deletes an Entity from the DB.
	 * @param key the ID of the Entity to be deleted.
	 */
	public void delete(Integer key);
	
	public List<Entity> list();
}
