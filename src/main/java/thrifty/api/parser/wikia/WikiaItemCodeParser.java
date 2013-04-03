package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.parser.ItemCodeParser;

@Component
public class WikiaItemCodeParser implements ItemCodeParser<WikiaText> {
    @Override
    public int extractItemCode(WikiaText parseable) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
