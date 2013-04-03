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

    public String getProperty(String propertyName) {
        String infoBox = getInfoBox();

        int startOfProperty = infoBox.indexOf("|" + propertyName + " = ") + (propertyName.length() + 4);
        int endOfProperty = startOfProperty;

        Pattern nextPropertyPattern = Pattern.compile("\\|\\w* = ");
        Matcher matcher = nextPropertyPattern.matcher(infoBox);

        if(matcher.find(startOfProperty)) {
            endOfProperty = matcher.start();
        }

        return infoBox.substring(startOfProperty, endOfProperty);
    }
}
