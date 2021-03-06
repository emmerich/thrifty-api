package thrifty.api.provider.itemlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thrifty.api.net.WikiaConnection;
import thrifty.api.parser.wikia.WikiaItemListParser;
import thrifty.api.parser.wikia.WikiaText;
import thrifty.api.provider.blacklist.ItemBlacklistImpl;

@Component
public class WikiaItemListProvider implements ItemListProvider {
	
	private static final String ITEM_LIST_WIKI_PAGE = "Template:Items";

	@Autowired
	private WikiaItemListParser parser;

    @Autowired
    private ItemBlacklistImpl blacklist;

	@Override
	public List<String> getItemList() {
		WikiaConnection connection = new WikiaConnection();
		List<String> list = parser.toItemList(new WikiaText(connection.get(ITEM_LIST_WIKI_PAGE), ITEM_LIST_WIKI_PAGE));
        list.removeAll(blacklist.blacklist());
        return list;
	}

}
