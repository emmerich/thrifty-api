package thrifty.api.parser;

import java.util.List;

import thrifty.api.model.Item;

public interface Parser {
	public Item toItem(Parseable parseableItem);
	public List<String> toItemList(Parseable parseableItemList);
}
