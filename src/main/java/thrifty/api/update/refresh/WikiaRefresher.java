package thrifty.api.update.refresh;

import java.util.List;

import thrifty.api.dao.entity.ItemDataAccess;
import thrifty.api.model.Item;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.wikia.WikiaTextParser;
import thrifty.api.provider.item.WikiaItemProvider;
import thrifty.api.provider.itemlist.WikiaItemListProvider;

public class WikiaRefresher implements Refresher {
	
	private WikiaItemListProvider itemListProvider;
	private WikiaItemProvider itemProvider;
	private WikiaTextParser textParser;
	
	private ItemDataAccess itemDao;

	@Override
	public void refresh() {
		List<String> items = itemListProvider.getItemList();
		
		for(String itemName : items) {
			Parseable parseable = itemProvider.getItemInformation(itemName);
			Item item = textParser.toItem(parseable);
			itemDao.update(item);
		}
	}
}
