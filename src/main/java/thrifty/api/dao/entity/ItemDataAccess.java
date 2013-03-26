package thrifty.api.dao.entity;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import thrifty.api.dao.JPAManagedDataAccess;

@Repository
@Transactional
public class ItemDataAccess extends JPAManagedDataAccess {

    private static final String LIST = "SELECT i FROM Item i";

	@Override
	public String getListQuery() {
		return LIST;
	}
}
