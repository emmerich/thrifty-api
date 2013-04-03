package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.GoldValue;
import thrifty.api.parser.GoldValueParser;

@Component
public class WikiaGoldValueParser implements GoldValueParser<WikiaText> {
    @Override
    public GoldValue extractSellValue(WikiaText parseable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public GoldValue extractCost(WikiaText parseable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
