package thrifty.api.parser.wikia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thrifty.api.parser.*;

@Component
public class WikiaItemParser extends ItemParserImpl {

    @Autowired
    public WikiaItemParser(WikiaStatParser statParser,
                           WikiaAvailabilityParser availabilityParser,
                           WikiaTierParser tierParser,
                           WikiaItemCodeParser itemCodeParser,
                           WikiaGoldValueParser goldValueParser,
                           WikiaPassiveParser passiveParser) {
        super(statParser, availabilityParser, tierParser, itemCodeParser, goldValueParser, passiveParser);
    }
}
