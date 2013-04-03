package thrifty.api.parser;

import thrifty.api.model.Stat;
import thrifty.api.model.effect.Statistic;

import java.util.Set;

public interface StatParser<T extends Parseable> {
    public Stat toStat(String statString);
    public Set<Statistic> extractStatisticSet(T parseable);

}
