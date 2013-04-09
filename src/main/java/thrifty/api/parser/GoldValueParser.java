package thrifty.api.parser;

import thrifty.api.model.GoldValue;

public interface GoldValueParser<T extends Parseable> {
    public int extractSellValue(T parseable);
    public int extractCost(T parseable);
}
