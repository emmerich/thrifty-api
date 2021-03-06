package thrifty.api.parser.wikia;

import org.apache.log4j.Logger;
import thrifty.api.parser.Parseable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiaText implements Parseable {
    private static final Logger LOG = Logger.getLogger(WikiaText.class);
	
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
        String infoBoxTag = text.indexOf("{{infobox") > 0 ? "{{infobox" : "{{Infobox";
        int startOfInfoBox = text.indexOf(infoBoxTag) + infoBoxTag.length();
        int endOfInfoBox = getEndOfInfoBox(startOfInfoBox);

        return text.substring(startOfInfoBox, endOfInfoBox);
    }

    private int getEndOfInfoBox(int startOfInfoBox) {
        // There may be other {{}} tags in the infobox, we need to ignore these
        int curlyBracesCounter = 0;

        for(int i = startOfInfoBox; i<text.length(); i++) {
            if(text.charAt(i) == '{' && text.charAt(i + 1) == '{') {
                // We have seen an opening bracket, increment counter
                curlyBracesCounter++;
            }

            if(text.charAt(i) == '}' && text.charAt(i + 1) == '}') {
                // We have seen a closing bracket
                if(curlyBracesCounter == 0) {
                    // This is the end of the info box
                    return i;
                } else {
                    curlyBracesCounter--;
                }
            }
        }

        return -1;
    }

    public String getProperty(String propertyName) throws NoSuchFieldException {
        LOG.debug("Getting property [" + propertyName + "] from page [" + pageName + "].");

        String infoBox = getInfoBox();

        Pattern startOfPropertyPattern = Pattern.compile(propertyName + ".*?=.*? ");
        Matcher matcher = startOfPropertyPattern.matcher(infoBox);

        if(matcher.find()) {
            int startOfProperty = matcher.end();
            int endOfProperty = getEndOfProperty(infoBox, startOfProperty);

            return infoBox.substring(startOfProperty, endOfProperty).replace("\\n", "");
        }

        throw new NoSuchFieldException("No such field: " + propertyName + " on item: " + pageName);
    }

    private int getEndOfProperty(String text, int startOfProperty) {
        boolean areInLinkElement = false;
        boolean areInTemplateElement = false;

        for(int i = startOfProperty; i<text.length(); i++) {
            if(text.charAt(i) == '[' && text.charAt(i + 1)  == '[') {
                areInLinkElement = true;
            }

            if(text.charAt(i) == '{' && text.charAt(i + 1) == '{') {
                areInTemplateElement = true;
            }

            if(text.charAt(i) == ']' && text.charAt(i + 1) == ']') {
                areInLinkElement = false;
            }

            if(text.charAt(i) == '}' && text.charAt(i + 1) == '}') {
                areInTemplateElement = false;
            }

            if(text.charAt(i) == '|' && !areInLinkElement && !areInTemplateElement) {
                return i;
            }

            if(i == (text.length() - 1)) {
                return i;
            }
        }

        return -1;
    }
}
