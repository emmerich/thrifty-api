package thrifty.api.controller;

import java.util.List;

import thrifty.api.model.Entity;

/**
 * A Controller that provides the GET HTTP request
 * type.
 * 
 * @author emmerich
 */
public interface GETController {
	
	/**
	 * Returns the Entity at the given ID.
	 * @param id the ID to look up.
	 * @return the Entity matching that ID.
	 */
	public Entity get(Integer id);
	
	/**
	 * Returns a list of all Entities handled by
	 * this Controller.
	 * 
	 * @return all Entities handled by this Controller.
	 */
	public List<Entity> list();
}
