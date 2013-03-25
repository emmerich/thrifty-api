package thrifty.api.dao.patch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import thrifty.api.model.patch.PatchNumber;

@Repository
public class CurrentPatchDataAccess {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public PatchNumber currentPatch() {
		//return entityManager.find(PatchNumber.class, );
	}
	
	public void currentPatch(PatchNumber number) {
		
	}

}
