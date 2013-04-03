package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.Tier;
import thrifty.api.parser.TierParser;

@Component
public class WikiaTierParser implements TierParser<WikiaText> {
    @Override
    public Tier extractTier(WikiaText parseable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
