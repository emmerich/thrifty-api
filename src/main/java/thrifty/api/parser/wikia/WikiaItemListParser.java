package thrifty.api.parser.wikia;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import thrifty.api.parser.ItemListParser;
import thrifty.api.parser.Parseable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WikiaItemListParser implements ItemListParser<WikiaText> {

    private static Logger LOG = Logger.getLogger(WikiaItemListParser.class);

    public List<String> toItemList(WikiaText parseable) {
        // We use a Set because items may be repeated
        Set<String> items = new HashSet<String>();

        String wikiText = parseable.getStringValue();

        // Everything after removed items we aren't interested in, so strip it down
        wikiText = wikiText.substring(0, wikiText.indexOf("Removed items"));

        String itemPatternStr = "\\{\\{iio\\|[\\w '\\n\\\\]*}}";
        Pattern itemPattern = Pattern.compile(itemPatternStr);
        Matcher matcher = itemPattern.matcher(wikiText);

        while(matcher.find()) {
            // u200e - UNICODE left-to-right mark
            String item = matcher.group()
                    .replace("\n", "")
                    .replace("{{iio|", "")
                    .replace("}}", "")
                    .replaceAll("[\\\\]", "") // RTL Unicode character
                    .replaceAll("u200e", "");

            items.add(item);
        }

        return new ArrayList<String>(items);
    }
}
