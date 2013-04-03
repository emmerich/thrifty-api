package thrifty.api.parser;

import thrifty.api.model.Availability;

import java.util.Set;

public interface AvailabilityParser<T extends Parseable> {
    public Set<Availability> extractAvailabilitySet(T parseable);
}
