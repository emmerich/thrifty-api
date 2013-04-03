package thrifty.api.update.refresh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thrifty.api.dao.entity.ItemDataAccess;
import thrifty.api.model.Item;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.wikia.WikiaItemParser;
import thrifty.api.provider.item.WikiaItemProvider;
import thrifty.api.provider.itemlist.WikiaItemListProvider;

@Component
public class WikiaRefresher implements Refresher {
	
	@Autowired
	private WikiaItemListProvider itemListProvider;
	
	@Autowired
	private WikiaItemProvider itemProvider;
	
	@Autowired
	private WikiaItemParser textParser;
	
	@Autowired
	private ItemDataAccess itemDao;

	@Override
	public void refresh() {
		List<String> items = itemListProvider.getItemList();
		
		for(String itemName : items) {
			Parseable parseable = itemProvider.getItemInformation(itemName);
			Item item = textParser.toItem(parseable);
			itemDao.create(item);
		}
	}
}
