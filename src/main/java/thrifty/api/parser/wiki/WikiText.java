package thrifty.api.parser.wiki;

import thrifty.api.parser.Parseable;

public class WikiText implements Parseable {
	
	private String text;
	
	public WikiText(String text) {
		 this.text = text;
	}

	public String getStringValue() {
		return text;
	}

}
