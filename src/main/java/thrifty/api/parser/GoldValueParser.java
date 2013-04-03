package thrifty.api.parser;

import thrifty.api.model.GoldValue;

public interface GoldValueParser<T extends Parseable> {
    public GoldValue extractSellValue(T parseable);
    public GoldValue extractCost(T parseable);
}
