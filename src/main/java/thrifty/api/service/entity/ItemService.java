package thrifty.api.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thrifty.api.dao.DataAccess;
import thrifty.api.dao.entity.ItemDataAccess;
import thrifty.api.service.DataAccessService;

@Service
public class ItemService extends DataAccessService {

    @Autowired
    public ItemService(ItemDataAccess dao) {
        this.dataAccess = dao;
    }
}
