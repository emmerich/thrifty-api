package thrifty.api.provider.item;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

import thrifty.api.net.UrlConnection;
import thrifty.api.net.WikiaConnection;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.wikia.WikiaText;

@Component
public class WikiaItemProvider implements ItemProvider {
	
	public Parseable getItemInformation(String itemName) {
		UrlConnection connection = new WikiaConnection();
		String wikiText = null;
		
		try {
			wikiText = connection.get(encode(itemName));
		} catch(UnsupportedEncodingException e) {
			// TODO(shall): Handle better
			e.printStackTrace();
		}
		
		return new WikiaText(wikiText, itemName);
		
	}
	
	private String encode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8");
	}
}
