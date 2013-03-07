package thrifty.api.model;

/**
 * An Entity is a persistable object.
 * 
 * @author emmerich
 */
public interface Entity {
	
	/**
	 * Get the Entity's ID (unique identifier)
	 * @return the ID of the Entity.
	 */
	public Integer id();
}
