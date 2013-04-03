package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.Availability;
import thrifty.api.parser.AvailabilityParser;

import java.util.Set;

@Component
public class WikiaAvailabilityParser implements AvailabilityParser<WikiaText> {
    @Override
    public Set<Availability> extractAvailabilitySet(WikiaText parseable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
