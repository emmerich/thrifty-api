package thrifty.api.dao.patch;

import org.springframework.stereotype.Repository;

import thrifty.api.dao.JPAManagedDataAccess;
import thrifty.api.model.patch.PatchUpdate;

@Repository
public class PatchUpdateDataAccess extends JPAManagedDataAccess {
	
	private static final String LIST = "SELECT p FROM PatchUpdate p";
	
	public PatchUpdate mostRecentPatch() {
		// Return the PatchUpdate with the most recent timestamp
		// TODO(emmerich): implement
		return null;
	}

	@Override
	public String getListQuery() {
		return LIST;
	}

}
