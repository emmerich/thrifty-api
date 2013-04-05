package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.Tier;
import thrifty.api.parser.TierParser;

import java.util.HashMap;
import java.util.Map;

@Component
public class WikiaTierParser implements TierParser<WikiaText> {

    private Map<String, Tier> lookup;

    public WikiaTierParser() {
        lookup = new HashMap<String, Tier>();

        lookup.put("basic", Tier.BASIC);
        lookup.put("advanced", Tier.ADVANCED);
        lookup.put("legendary", Tier.LEGENDARY);
        lookup.put("mythical", Tier.MYTHICAL);
        lookup.put("consumable", Tier.CONSUMABLE);
    }

    @Override
    public Tier extractTier(WikiaText parseable) {

        try {
            String tier = parseable.getProperty("type");

            // Tier is in the format Advanced item or [[Advanced item|Advanced]]
            // If it's a link, extract the named part
            if(tier.contains("|")) {
                tier = tier.split("\\|")[1].replace("]]", "");
            }

            // Now convert that tier string into a tier enum
            return lookup.get(tier.toLowerCase());
        } catch(NoSuchFieldException e) {
            // Every item should have a tier, I think, so print stack trace here
            e.printStackTrace();
        }

        return null;
    }
}
