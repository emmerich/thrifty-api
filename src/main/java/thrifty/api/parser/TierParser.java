package thrifty.api.parser;

import thrifty.api.model.Tier;

public interface TierParser<T extends Parseable> {
    public Tier extractTier(T parseable);
}
