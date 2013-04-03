package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.Availability;
import thrifty.api.parser.AvailabilityParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class WikiaAvailabilityParser implements AvailabilityParser<WikiaText> {

    @Override
    public Set<Availability> extractAvailabilitySet(WikiaText parseable) {
        Set<Availability> result = new HashSet<Availability>();

        try {
            String availabilityString = parseable.getProperty("mode");

            // T = Twisted Treeline, D = Dominion, P = Proving Grounds, S = Summoner's Rift

            if(availabilityString.contains("T")) {
                result.add(Availability.TWISTED_TREELINE);
            }

            if(availabilityString.contains("D")) {
                result.add(Availability.DOMINION);
            }

            if(availabilityString.contains("P")) {
                result.add(Availability.PROVING_GROUNDS);
            }

            if(availabilityString.contains("S")) {
                result.add(Availability.SUMMONERS_RIFT);
            }
        } catch (NoSuchFieldException e) {
            // No mode field means it is available on all (Common)
            result.addAll(Arrays.asList(Availability.values()));
        }

        return result;
    }
}
