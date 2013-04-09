package thrifty.api.parser.wikia;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import thrifty.api.model.Tier;
import thrifty.api.parser.TierParser;

import java.util.HashMap;
import java.util.Map;

@Component
public class WikiaTierParser implements TierParser<WikiaText> {

    private static final Logger LOG = Logger.getLogger(WikiaTierParser.class);

    private Map<String, Tier> lookup;

    public WikiaTierParser() {
        lookup = new HashMap<String, Tier>();

        lookup.put("basic", Tier.BASIC);
        lookup.put("advanced", Tier.ADVANCED);
        lookup.put("legendary", Tier.LEGENDARY);
        lookup.put("mythical", Tier.MYTHICAL);
        lookup.put("consumable", Tier.CONSUMABLE);
        lookup.put("enchantment", Tier.ENCHANTMENT);
    }

    @Override
    public Tier extractTier(WikiaText parseable) {

        Tier result = null;

        try {
            String tier = parseable.getProperty("type");
            tier = tier.replace("\\n", "").replace(" ", "").replace("]]", "").replace("[[", "");

            // Tier is in the format Advanced item or [[Advanced item|Advanced]]
            // If it's a link, extract the named part
            if(tier.contains("|")) {
                tier = tier.split("\\|")[1];
            }

            // Now convert that tier string into a tier enum
            if(tier.length() == 0) {
                return Tier.BASIC;
            }

            result = lookup.get(tier.toLowerCase());

            if(result == null) {
                // There was an error, exception!
                LOG.warn("Error parsing tier: " + tier.toLowerCase());
            }
        } catch(NoSuchFieldException e) {
            // Every item should have a tier, I think, so print stack trace here
            e.printStackTrace();
        }

        return result;
    }
}
