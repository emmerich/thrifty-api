package thrifty.api.parser;

import thrifty.api.model.Stat;

public interface StatParser {
    public Stat toStat(String statString);
}
