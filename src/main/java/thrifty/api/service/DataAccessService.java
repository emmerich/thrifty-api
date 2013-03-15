package thrifty.api.service;

import java.util.List;

import thrifty.api.dao.DataAccess;
import thrifty.api.model.Entity;

public class DataAccessService implements Service {
	
	private DataAccess dataAccess;

	public void create(Entity creation) {
		dataAccess.create(creation);
	}

	public Entity read(Integer key) {
		return dataAccess.read(key);
	}

	public void update(Entity updated) {
		dataAccess.update(updated);
	}

	public void delete(Integer key) {
		dataAccess.delete(key);
	}

	public List<Entity> list() {
		return dataAccess.list();
	}

}
