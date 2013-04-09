package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.parser.ItemCodeParser;

@Component
public class WikiaItemCodeParser implements ItemCodeParser<WikiaText> {

    @Override
    public int extractItemCode(WikiaText parseable) {
        Integer result = null;

        try {
            String code = parseable.getProperty("code");
            code = code.replaceAll("\\\\", "");
            result = Integer.parseInt(code);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return result;
    }
}
