package thrifty.api.parser;

import thrifty.api.model.Item;

public abstract class ItemParserImpl implements ItemParser {

    private StatParser statParser;
    private AvailabilityParser availabilityParser;
    private TierParser tierParser;
    private ItemCodeParser itemCodeParser;
    private GoldValueParser goldValueParser;

    public ItemParserImpl(StatParser statParser,
                          AvailabilityParser availabilityParser,
                          TierParser tierParser,
                          ItemCodeParser itemCodeParser,
                          GoldValueParser goldValueParser) {
        this.statParser = statParser;
        this.availabilityParser = availabilityParser;
        this.tierParser = tierParser;
        this.itemCodeParser = itemCodeParser;
        this.goldValueParser = goldValueParser;
    }

    @Override
    public Item toItem(Parseable parseableItem) {
        Item result = new Item();

        try {
            result.name(parseableItem.getPageName());
            result.addStatistic(statParser.extractStatisticSet(parseableItem));
            result.addAvailability(availabilityParser.extractAvailabilitySet(parseableItem));
            result.tier(tierParser.extractTier(parseableItem));
            result.itemCode(itemCodeParser.extractItemCode(parseableItem));
            result.sellValue(goldValueParser.extractSellValue(parseableItem));
            result.cost(goldValueParser.extractCost(parseableItem));
        } catch(Exception e) {
            e.printStackTrace();
        }

        // auras, passives, actives

        return result;
    }
}
