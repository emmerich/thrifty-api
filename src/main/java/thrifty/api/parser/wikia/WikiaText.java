package thrifty.api.parser.wikia;

import thrifty.api.parser.Parseable;

public class WikiaText implements Parseable {
	
	private String text;
	private String itemName;
	
	public WikiaText(String text, String itemName) {
		 this.text = text;
		 this.itemName = itemName;
	}

	public String getStringValue() {
		return text;
	}
	
	public String getItemName() {
		return itemName;
	}

}
