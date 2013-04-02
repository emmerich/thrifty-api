package thrifty.api.parser.wikia;

import thrifty.api.parser.Parseable;

public class WikiaText implements Parseable {
	
	private String text;
	private String pageName;
	
	public WikiaText(String text, String pageName) {
		 this.text = text;
		 this.pageName = pageName;
	}

	public String getStringValue() {
		return text;
	}
	
	public String getPageName() {
		return pageName;
	}

}
