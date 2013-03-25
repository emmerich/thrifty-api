package thrifty.api.provider.item;

import thrifty.api.parser.Parseable;

public interface ItemProvider {
	public Parseable getItemInformation(String itemName);
}
