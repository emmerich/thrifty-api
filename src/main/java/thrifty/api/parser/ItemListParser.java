package thrifty.api.parser;

import thrifty.api.model.Item;

import java.util.List;

public interface ItemListParser<T extends Parseable> {
    public List<String> toItemList(T parseableItemList);
}

