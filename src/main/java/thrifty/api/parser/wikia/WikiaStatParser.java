package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.Stat;
import thrifty.api.model.effect.Statistic;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.StatParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class WikiaStatParser implements StatParser<WikiaText> {

    private Map<String, Stat> lookup;

    public WikiaStatParser() {
        lookup = new HashMap<String, Stat>();

        lookup.put("armor penetration", Stat.ARMOR_PENETRATION);
        lookup.put("attack damage", Stat.ATTACK_DAMAGE);
        lookup.put("attack speed", Stat.ATTACK_SPEED);
        lookup.put("critical strike chance", Stat.CRITICAL_STRIKE_CHANCE);
        lookup.put("critical strike damage", Stat.CRITICAL_STRIKE_DAMAGE);
        lookup.put("life steal", Stat.LIFE_STEAL);
        lookup.put("movement speed", Stat.MOVEMENT_SPEED);
        lookup.put("armor", Stat.ARMOR);
        lookup.put("health", Stat.HEALTH);
        lookup.put("health regeneration", Stat.HEALTH_REGENERATION);
        lookup.put("health regen", Stat.HEALTH_REGENERATION);
        lookup.put("magic resistance", Stat.MAGIC_RESISTANCE);
        lookup.put("tenacity", Stat.TENACITY);
        lookup.put("ability power", Stat.ABILITY_POWER);
        lookup.put("cooldown reduction", Stat.COOLDOWN_REDUCTION);
        lookup.put("magic penetration", Stat.MAGIC_PENETRATION);
        lookup.put("mana", Stat.MANA);
        lookup.put("mana regen", Stat.MANA_REGENERATION);
        lookup.put("mana regeneration", Stat.MANA_REGENERATION);
        lookup.put("spell vamp", Stat.SPELL_VAMP);
    }

    @Override
    public Stat toStat(String statString) {
        return lookup.get(statString);
    }

    @Override
    public Set<Statistic> extractStatisticSet(WikiaText parseable) {
        Set<Statistic> result = new HashSet<Statistic>();

        try {
            // Statistics are contained under the 'effects' key:
            String effects = parseable.getProperty("effects");

            // As this is wiki text, the pattern may contain some HTML that we don't want (such as breaks). Remove
            // anything between <> tags
            Pattern htmlPattern = Pattern.compile("<.*>");
            effects = htmlPattern.matcher(effects).replaceAll("");

            // Our string will look something like this:
            // +80 [[health]]+10 [[attack damage]]
            // Split the string on ]], separating each stat
            String[] individualStats = effects.split("]]");

            // Now iterate over each stat, and extract the Statistic object from it
            for(String individualStatString : individualStats) {
                Statistic stat = extractStatistic(individualStatString);

                if(stat != null) {
                    result.add(stat);
                }
            }
        } catch(NoSuchFieldException e) {
            // No such field might just mean this item has no effects, in which case return the empty set.
        }

        System.out.println("Statistics: "  + result);

        return result;
    }

    private Statistic extractStatistic(String statString) {
        // The string will be in the format:
        // +80 [[health
        // as we removed the ]] in the previous split

        // Firstly, seperate the field and value by spliting on [[
        String[] components = statString.split("\\[\\[");

        // We only have a valid stat if there are 2 components
        if(components.length == 2) {
            // Extract the value. The value may be a percentage, so remove that
            // TODO(shall): Handle percentage values properly here
            Integer value = Integer.parseInt(components[0].trim().replace("%", ""));
            String field = components[1];

            // Some people like to define user-friendly names for statistics like so:
            // +7 [[mana regen|Mana Regeneration Per 5 Seconds]]
            // The important part is the link to the page, which is before the bar. If we have a bar in the field,
            // make sure we only take the link
            if(field.contains("|")) {
                field = field.split("\\|")[0];
            }

            // Finally, we need to derive the enumerated Stat from the field string. Do that.
            // Lowercase because there aren't any casing conventions for wiki text
            Stat statEnum = toStat(field.toLowerCase());

            // Now we've got our components
            return new Statistic(statEnum, value);
        }

        // TODO(shall): Exception thrown here
        return null;
    }
}
