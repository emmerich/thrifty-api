package thrifty.api.parser.wikia;

import thrifty.api.parser.Parseable;

public class WikiaText implements Parseable {
	
	private String text;
	
	public WikiaText(String text) {
		 this.text = text;
	}

	public String getStringValue() {
		return text;
	}

}
