package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.parser.ItemListParser;
import thrifty.api.parser.Parseable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WikiaItemListParser implements ItemListParser<WikiaText> {

    public List<String> toItemList(WikiaText parseable) {
        List<String> items = new ArrayList<String>();
        String wikiText = parseable.getStringValue();

        String itemPatternStr = "\\{\\{iio\\|[\\w '\\n\\\\]*}}";
        Pattern itemPattern = Pattern.compile(itemPatternStr);
        Matcher matcher = itemPattern.matcher(wikiText);

        while(matcher.find()) {
            String item = matcher.group().replace("\n", "").replace("{{iio|", "").replace("}}", "");
            items.add(item);
        }

        return items;
    }
}
