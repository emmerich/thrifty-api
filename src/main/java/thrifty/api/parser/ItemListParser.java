package thrifty.api.parser;

import thrifty.api.model.Item;

import java.util.List;

public interface ItemListParser {
    public List<String> toItemList(Parseable parseableItemList);
}

