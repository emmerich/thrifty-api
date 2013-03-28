package thrifty.api.parser.wikia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import thrifty.api.model.Item;
import thrifty.api.model.Stat;
import thrifty.api.model.effect.Effect;
import thrifty.api.model.effect.Statistic;
import thrifty.api.model.effect.UniqueableEffect;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.Parser;

@Component
public class WikiaTextParser implements Parser {
	
	/**
	 * Test only - delete this eventually
	 * @param args
	 */
	public static void main(String[] args) {
		WikiaText text = new WikiaText("{\"query\":{\"pages\":{\"2167\":{\"pageid\":2167,\"ns\":0,\"title\":\"Infinity Edge\",\"revisions\":[{\"*\":\"{{infobox item\\n|name    = Infinity Edge\\n|caption = \"When the sun is at its peak, the pool blends in with the shimmering heat, looking as if its waters run off into infinity.\"\\n|type    = [[Advanced item|Advanced]]\\n|effects = +70 [[attack damage]]<br \\/>+25% [[critical strike chance]]\\n|passive = Unique: +50% [[critical strike damage]].\\n|menu    = Attack > Critical Strike<br \\/>Attack > Damage\\n|buy     = 3800g (645g)\\n|sell    = 2660g\\n|code    = 3031\\n}}\\n\\n'''Infinity Edge''' is an [[advanced item|advanced]] [[item]] in [[League of Legends]].<ref>[http:\\/\\/www.leagueoflegends.com\\/items#3031 Infinity Edge item page] at the [[Official Web Page]].<\\/ref>\\n\\n== Recipe ==\\n* {{ii|B. F. Sword}} (1550g)\\n* {{ii|Pickaxe}} (875g)\\n* {{ii|Cloak of Agility}} (730g)\\n* 645g\\n\\n== Cost Analysis ==\\n{{gold value}}\\n* 70 [[attack damage]] = 2800g\\n* 25% [[critical strike chance]] = 1250g\\n** '''Total Gold Value''' = 4050g\\n\\n{{gold efficiency}}\\n* {{ii|Infinity Edge}} is gold efficient even without passive.\\n\\n== Background ==\\n''Only the boldest of men and women dare take the risky trip to the hottest spot in [[Valoran]], in the very center of the [[Shurima Desert]]. Those who successfully make the journey may bathe their swords in a magical pool of the purest water known to this world. Any edge dipped into this pool will stay sharper and strike truer than any other sword.''\\n\\n== Trivia ==\\n* {{ii|Infinity Edge}} shares its name with a set of Infinity Weapons from the game ''[[Wikipedia:Lineage II|Lineage II]],'' which are the strongest weapons available. {{ii|Infinity Edge}} looks almost exactly like such weapons.\\n* Critical strike damage [[Stacking|stacks additively]], so it works together with other critical strike damage effects (generally [[List of runes#Furor|runes of Furor]] and\\/or {{mi|Lethality}}).\\n* {{ii|Infinity Edge}} is the only item that increases critical strike damage.\\n\\n== Patch History ==\\n{{scroll box|content=\\n'''[[V1.0.0.152]]:'''\\n* Item cost reduced to 3800 from 3830.\\n* Attack damage reduced to 70 from 80.\\n\\n'''[[V1.0.0.115]]:'''\\n* Total cost is now 3830 gold from 4080 gold.\\n* Attack damage increased to 80 from 75.\\n* Critical strike chance increased to 25% from 20%.\\n'''[[V1.0.0.107]]:'''\\n* Attack damage reduced to 75 from 80.\\n'''[[V0.9.22.16]]:'''\\n* Issue with scaling with crit talents\\/runes fixed.\\n'''[[May 15, 2009 Patch]]:'''\\n* Fixed a bug where certain item effects would persist past selling the item.\\n'''[[April 18, 2009 Patch]]:'''\\n* {{ii|Infinity Edge}}: effect made Unique, having multiple Infinity Edges will not stack their passive ability.\\n'''[[April 11, 2009 Patch]]:'''\\n* {{ii|Infinity Edge}}: cost increased to 375 from 350.\\n}}\\n\\n== References ==\\n{{reflist}}\\n{{Items}}\\n[[Category:Critical strike items]]\\n[[Category:Attack damage items]]\\n[[Category:Advanced items]]\"}]}}}}", "Infinity Edge");
		WikiaTextParser parser = new WikiaTextParser();
		
		Item item = parser.toItem(text);
		
		System.out.println(item.name());
		System.out.println("Stats: " + item.effects());
	}

	/**
	 * Parses a Parseable item and returns an Item entity to be persisted.
	 */
	public Item toItem(Parseable parseableItem) {
		// The infoBox element contains all the information we need to parse the item.
		// Extract the element from the wikia text.
        String infoBox = extractInfoBox(parseableItem.getStringValue());

        Item item = new Item();
        
        // Not every infoBox contains the item's name, so just grab it from the parseable
        item.name(parseableItem.getItemName());
        
        // "regular" stats are ones that are not part of a unique passive, or anything like that
        Set<UniqueableEffect> regularStatistics = getEffectsForStatistics(getStats(infoBox, "effects"));
        item.effects(regularStatistics);

        return item;
	}
	
	public List<String> toItemList(Parseable parser) {
		List<String> test = new ArrayList<String>();
		test.add("Infinity Edge");
		test.add("Entropy");
		return test;
	}
	
	public Set<UniqueableEffect> getEffectsForStatistics(Map<String, Integer> stats) {
		Set<UniqueableEffect> result = new HashSet<UniqueableEffect>();
		
		for(String stat : stats.keySet()) {
			result.add(new Statistic(getStatFromStatisticString(stat), stats.get(stat)));
		}
		
		return result;
	}

    private Stat getStatFromStatisticString(String stat) {
    	if(stat.equals("critical strike chance")) {
    		return Stat.CRITICAL_STRIKE_CHANCE;
    	}
    	
    	if(stat.equals("attack damage")) {
    		return Stat.ATTACK_DAMAGE;
    	}
    	
		return null;
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
    
    private String extractInfoBox(String wikiTextString) {
    	int startOfInfoBox = wikiTextString.indexOf("{{infobox") + "{{infobox".length();
    	int endOfInfoBox = wikiTextString.indexOf("}}", startOfInfoBox);
    	
    	return wikiTextString.substring(startOfInfoBox, endOfInfoBox);
    }



}
