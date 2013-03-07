package thrifty.api.crawler;

import thrifty.api.parser.Parseable;

public interface ItemInfoProvider {
	public Parseable getItemInformation(String itemName);
}
