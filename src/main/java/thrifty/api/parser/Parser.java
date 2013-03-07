package thrifty.api.parser;

import thrifty.api.model.Item;

public interface Parser {
	public Item toItem(Parseable parseableItem);
}
