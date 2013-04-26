package thrifty.api.parser;

import org.apache.log4j.Logger;
import thrifty.api.model.Item;


public abstract class ItemParserImpl implements ItemParser {

    private static final Logger LOG = Logger.getLogger(ItemParserImpl.class);

    private StatParser statParser;
    private AvailabilityParser availabilityParser;
    private TierParser tierParser;
    private ItemCodeParser itemCodeParser;
    private GoldValueParser goldValueParser;

    private PassiveParser passiveParser;

    public ItemParserImpl(StatParser statParser,
                          AvailabilityParser availabilityParser,
                          TierParser tierParser,
                          ItemCodeParser itemCodeParser,
                          GoldValueParser goldValueParser,
                          PassiveParser passiveParser) {
        this.statParser = statParser;
        this.availabilityParser = availabilityParser;
        this.tierParser = tierParser;
        this.itemCodeParser = itemCodeParser;
        this.goldValueParser = goldValueParser;
        this.passiveParser = passiveParser;
    }

    @Override
    public Item toItem(Parseable parseableItem) {
        LOG.info("Getting item information for " + parseableItem.getPageName());

        Item result = new Item();

        try {
            result.name(parseableItem.getPageName());
            result.addStatistic(statParser.extractStatisticSet(parseableItem));
            result.addAvailability(availabilityParser.extractAvailabilitySet(parseableItem));
            result.tier(tierParser.extractTier(parseableItem));
            result.itemCode(itemCodeParser.extractItemCode(parseableItem));
            result.sellValue(goldValueParser.extractSellValue(parseableItem));
            result.cost(goldValueParser.extractCost(parseableItem));

            result.addPassive(passiveParser.extractPassiveSet(parseableItem));
        } catch(Exception e) {
            e.printStackTrace();
        }

        // auras, passives, actives

        return result;
    }
}
