package thrifty.api.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import thrifty.api.model.Entity;
import thrifty.api.model.Item;

public abstract class JPAManagedDataAccess implements DataAccess {
	
	@PersistenceContext
    protected EntityManager entityManager;

	@Override
    public void create(Entity creation) {
        entityManager.persist(creation);
        entityManager.flush();
    }

    @Override
    public Entity read(Integer key) {
        return entityManager.find(Item.class, key);
    }

    @Override
    public Entity update(Entity updated) {
        Entity merged = entityManager.merge(updated);
        entityManager.flush();
        return merged;
    }

    @Override
    public void delete(Integer key) {
        Entity entity = read(key);
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    public List<Entity> list() {
        Query query = entityManager.createQuery(getListQuery());
        return query.getResultList();
    }
    
    public abstract String getListQuery();

}
