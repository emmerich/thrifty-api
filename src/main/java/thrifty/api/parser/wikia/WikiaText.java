package thrifty.api.parser.wikia;

import thrifty.api.parser.Parseable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiaText implements Parseable {
	
	private String text;
	private String pageName;
	
	public WikiaText(String text, String pageName) {
		 this.text = text;
		 this.pageName = pageName;
	}

	public String getStringValue() {
		return text;
	}
	
	public String getPageName() {
		return pageName;
	}

    public String getInfoBox() {
        int startOfInfoBox = text.indexOf("{{infobox") + "{{infobox".length();
        int endOfInfoBox = text.indexOf("}}", startOfInfoBox);

        return text.substring(startOfInfoBox, endOfInfoBox);
    }

    public String getProperty(String propertyName) throws NoSuchFieldException {
        String infoBox = getInfoBox();

        Pattern startOfPropertyPattern = Pattern.compile(propertyName + ".*?=.*? ");
        Matcher matcher = startOfPropertyPattern.matcher(infoBox);

        if(matcher.find()) {
            int startOfProperty = matcher.end();
            int endOfProperty = infoBox.indexOf("|", startOfProperty);

            return infoBox.substring(startOfProperty, endOfProperty);
        }

        throw new NoSuchFieldException("No such field: " + propertyName + " on item: " + pageName);
    }
}
