package thrifty.api.provider.item;

import thrifty.api.net.UrlConnection;
import thrifty.api.net.WikiaConnection;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.wikia.WikiaText;

public class WikiaItemProvider implements ItemProvider {
	
	public Parseable getItemInformation(String itemName) {
		UrlConnection connection = new WikiaConnection();
		String wikiText = connection.get(itemName);
		return new WikiaText(wikiText);
	}
}
