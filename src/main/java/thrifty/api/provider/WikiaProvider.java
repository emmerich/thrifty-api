package thrifty.api.provider;

import thrifty.api.net.UrlConnection;
import thrifty.api.net.WikiaConnection;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.wiki.WikiText;

public class WikiaProvider implements DataProvider {
	
	public Parseable getItemInformation(String itemName) {
		UrlConnection connection = new WikiaConnection();
		String wikiText = connection.get(itemName);
		return new WikiText(wikiText);
	}
}
