package thrifty.api.parser;

import java.util.List;

import thrifty.api.model.Item;

public interface ItemParser<T extends Parseable> {
	public Item toItem(T parseableItem);
}