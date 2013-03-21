package thrifty.api.parser.wiki;

import thrifty.api.model.Item;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiTextParser implements Parser {

	public Item toItem(Parseable parseableItem) {
		//String wikiText = parseableItem.getStringValue();
        String wikiText = "{{" +
                "infobox item " +
                "|name = Infinity Edge" +
                "|caption = \"When the sun is at its peak, the pool blends in with the shimmering heat, looking as if its waters run off into infinity.\"" +
                "|type = [[Advanced item|Advanced]]" +
                "|effects = +70 [[attack damage]]<br />+25% [[critical strike chance]]" +
                "|passive = Unique: +50% [[critical strike damage]]." +
                "|menu = Attack > Critical Strike<br />Attack > Damage" +
                "|buy = 3800g (645g)" +
                "|sell = 2660g" +
                "|code = 3031 }}" +

                "'''Infinity Edge''' is an [[advanced item|advanced]] [[item]] in [[League of Legends]].<ref>[http://www.leagueoflegends.com/items#3031 Infinity Edge item page] at the [[Official Web Page]].</ref> == Recipe == * {{ii|B. F. Sword}} (1550g) * {{ii|Pickaxe}} (875g) * {{ii|Cloak of Agility}} (730g) * 645g == Cost Analysis == {{gold value}} * 70 [[attack damage]] = 2800g * 25% [[critical strike chance]] = 1250g ** '''Total Gold Value''' = 4050g {{gold efficiency}} * {{ii|Infinity Edge}} is gold efficient even without passive. == Background == ''Only the boldest of men and women dare take the risky trip to the hottest spot in [[Valoran]], in the very center of the [[Shurima Desert]]. Those who successfully make the journey may bathe their swords in a magical pool of the purest water known to this world. Any edge dipped into this pool will stay sharper and strike truer than any other sword.'' == Trivia == * {{ii|Infinity Edge}} shares its name with a set of Infinity Weapons from the game ''[[Wikipedia:Lineage II|Lineage II]],'' which are the strongest weapons available. {{ii|Infinity Edge}} looks almost exactly like such weapons. * Critical strike damage [[Stacking|stacks additively]], so it works together with other critical strike damage effects (generally [[List of runes#Furor|runes of Furor]] and/or {{mi|Lethality}}). * {{ii|Infinity Edge}} is the only item that increases critical strike damage. == Patch History == {{scroll box|content= '''[[V1.0.0.152]]:''' * Item cost reduced to 3800 from 3830. * Attack damage reduced to 70 from 80. '''[[V1.0.0.115]]:''' * Total cost is now 3830 gold from 4080 gold. * Attack damage increased to 80 from 75. * Critical strike chance increased to 25% from 20%. '''[[V1.0.0.107]]:''' * Attack damage reduced to 75 from 80. '''[[V0.9.22.16]]:''' * Issue with scaling with crit talents/runes fixed. '''[[May 15, 2009 Patch]]:''' * Fixed a bug where certain item effects would persist past selling the item. '''[[April 18, 2009 Patch]]:''' * {{ii|Infinity Edge}}: effect made Unique, having multiple Infinity Edges will not stack their passive ability. '''[[April 11, 2009 Patch]]:''' * {{ii|Infinity Edge}}: cost increased to 375 from 350. }} == References == {{reflist}} {{Items}} [[Category:Critical strike items]] [[Category:Attack damage items]] [[Category:Advanced items]]";

        int infoboxStart = wikiText.indexOf("{{") + 2;
        int infoboxEnd = wikiText.indexOf("}}", infoboxStart);
        String infoBox = wikiText.substring(infoboxStart, infoboxEnd);

        Item item = new Item();
        item.name(getProperty(wikiText, "name"));

        return item;
	}

    public String getProperty(String container, String property) {
        int startOfProperty = container.indexOf("|" + property + " = ") + (property.length() + 4);
        int endOfProperty = startOfProperty;

        Pattern nextPropertyPattern = Pattern.compile("\\|\\w* = ");
        Matcher matcher = nextPropertyPattern.matcher(container);

        if(matcher.find(startOfProperty)) {
            endOfProperty = matcher.start();
        }

        return container.substring(startOfProperty, endOfProperty);
    }

    public Map<String, Integer> getStats(String container, String property) {
        Map<String, Integer> result = new HashMap<String, Integer>();

        String effectsStr = getProperty(container, property);

        Pattern breakPattern = Pattern.compile("<.*>");
        String htmlEscaped = breakPattern.matcher(effectsStr).replaceAll("");

        String[] stats = htmlEscaped.split("]]");

        for(String stat : stats) {
            try {
                result.putAll(parseStatistic(stat));
            } catch(Exception e) {
                System.out.println("Not a stat: " + stat);
            }
        }

        return result;
    }

    public Map<String, Integer> parseStatistic(String stat) throws Exception {
        Map<String, Integer> result = new HashMap<String, Integer>();

        String[] components = stat.split("\\[\\[");

        if(components.length == 2) {
            try {
                Integer value = Integer.parseInt(components[0].replace(" ", "").replace("%", "").replace("Unique:", ""));
                String field = components[1];

                if(isValidStat(field)) {
                    result.put(field, value);
                } else {
                    throw new Exception("Not a stat");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Not a stat");
            }
        } else {
            throw new Exception("Not a stat");
        }

        return result;
    }

    public boolean isValidStat(String field) {
        return true;
    }



}
