package thrifty.api.parser;

import java.util.List;

import thrifty.api.model.Item;

public interface ItemParser {
	public Item toItem(Parseable parseableItem);
}