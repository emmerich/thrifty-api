package thrifty.api.parser;

import thrifty.api.model.effect.Passive;

import java.util.Set;

public interface PassiveParser<T extends Parseable> {
    public Set<Passive> extractPassiveSet(T parseable);
}
