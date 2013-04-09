package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.parser.ItemCodeParser;

@Component
public class WikiaItemCodeParser implements ItemCodeParser<WikiaText> {

    public static void main(String [] args) {
        WikiaItemCodeParser p = new WikiaItemCodeParser();
        String t = "{\\\"query\\\":{\\\"pages\\\":{\\\"2167\\\":{\\\"pageid\\\":2167,\\\"ns\\\":0,\\\"title\\\":\\\"Infinity Edge\\\",\\\"revisions\\\":[{\\\"*\\\":\\\"{{infobox item\\\\n|name    = Infinity Edge\\\\n|caption = \\\"When the sun is at its peak, the pool blends in with the shimmering heat, looking as if its waters run off into infinity.\\\"\\\\n|type    = [[Advanced item|Advanced]]\\\\n|effects = +70 [[attack damage]]<br \\\\/>+25% [[critical strike chance]]\\\\n|passive = Unique: +50% [[critical strike damage]].\\\\n|menu    = Attack > Critical Strike<br \\\\/>Attack > Damage\\\\n|buy     = 3800g (645g)\\\\n|sell    = 2660g\\\\n|code    = 3031\\\\n}}\\\\n\\\\n'''Infinity Edge''' is an [[advanced item|advanced]] [[item]] in [[League of Legends]].<ref>[http:\\\\/\\\\/www.leagueoflegends.com\\\\/items#3031 Infinity Edge item page] at the [[Official Web Page]].<\\\\/ref>\\\\n\\\\n== Recipe ==\\\\n* {{ii|B. F. Sword}} (1550g)\\\\n* {{ii|Pickaxe}} (875g)\\\\n* {{ii|Cloak of Agility}} (730g)\\\\n* 645g\\\\n\\\\n== Cost Analysis ==\\\\n{{gold value}}\\\\n* 70 [[attack damage]] = 2800g\\\\n* 25% [[critical strike chance]] = 1250g\\\\n** '''Total Gold Value''' = 4050g\\\\n\\\\n{{gold efficiency}}\\\\n* {{ii|Infinity Edge}} is gold efficient even without passive.\\\\n\\\\n== Background ==\\\\n''Only the boldest of men and women dare take the risky trip to the hottest spot in [[Valoran]], in the very center of the [[Shurima Desert]]. Those who successfully make the journey may bathe their swords in a magical pool of the purest water known to this world. Any edge dipped into this pool will stay sharper and strike truer than any other sword.''\\\\n\\\\n== Trivia ==\\\\n* {{ii|Infinity Edge}} shares its name with a set of Infinity Weapons from the game ''[[Wikipedia:Lineage II|Lineage II]],'' which are the strongest weapons available. {{ii|Infinity Edge}} looks almost exactly like such weapons.\\\\n* Critical strike damage [[Stacking|stacks additively]], so it works together with other critical strike damage effects (generally [[List of runes#Furor|runes of Furor]] and\\\\/or {{mi|Lethality}}).\\\\n* {{ii|Infinity Edge}} is the only item that increases critical strike damage.\\\\n\\\\n== Patch History ==\\\\n{{scroll box|content=\\\\n'''[[V1.0.0.152]]:'''\\\\n* Item cost reduced to 3800 from 3830.\\\\n* Attack damage reduced to 70 from 80.\\\\n\\\\n'''[[V1.0.0.115]]:'''\\\\n* Total cost is now 3830 gold from 4080 gold.\\\\n* Attack damage increased to 80 from 75.\\\\n* Critical strike chance increased to 25% from 20%.\\\\n'''[[V1.0.0.107]]:'''\\\\n* Attack damage reduced to 75 from 80.\\\\n'''[[V0.9.22.16]]:'''\\\\n* Issue with scaling with crit talents\\\\/runes fixed.\\\\n'''[[May 15, 2009 Patch]]:'''\\\\n* Fixed a bug where certain item effects would persist past selling the item.\\\\n'''[[April 18, 2009 Patch]]:'''\\\\n* {{ii|Infinity Edge}}: effect made Unique, having multiple Infinity Edges will not stack their passive ability.\\\\n'''[[April 11, 2009 Patch]]:'''\\\\n* {{ii|Infinity Edge}}: cost increased to 375 from 350.\\\\n}}\\\\n\\\\n== References ==\\\\n{{reflist}}\\\\n{{Items}}\\\\n[[Category:Critical strike items]]\\\\n[[Category:Attack damage items]]\\\\n[[Category:Advanced items]]\\\"}]}}}}";

        System.out.println(p.extractItemCode(new WikiaText(t, null)));
    }

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
