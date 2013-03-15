package thrifty.api.provider;

import thrifty.api.parser.Parseable;

public interface DataProvider {
	public Parseable getItemInformation(String itemName);
}
