package thrifty.api.provider.itemlist;

import java.util.List;

import thrifty.api.net.WikiaConnection;
import thrifty.api.parser.wikia.WikiaText;
import thrifty.api.parser.wikia.WikiaTextParser;

public class WikiaItemListProvider implements ItemListProvider {
	
	private WikiaTextParser parser;

	@Override
	public List<String> getItemList() {
		WikiaConnection connection = new WikiaConnection();
		return parser.toItemList(new WikiaText(connection.get("Items")));
	}

}
