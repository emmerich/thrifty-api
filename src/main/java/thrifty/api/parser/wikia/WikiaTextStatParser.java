package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.model.Stat;
import thrifty.api.parser.StatParser;

import java.util.HashMap;
import java.util.Map;

@Component
public class WikiaTextStatParser implements StatParser {

    private Map<String, Stat> lookup;

    public WikiaTextStatParser() {
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
        lookup.put("magic resistance", Stat.MAGIC_RESISTANCE);
        lookup.put("tenacity", Stat.TENACITY);
        lookup.put("ability power", Stat.ABILITY_POWER);
        lookup.put("cooldown reduction", Stat.COOLDOWN_REDUCTION);
        lookup.put("magic penetration", Stat.MAGIC_PENETRATION);
        lookup.put("mana", Stat.MANA);
        lookup.put("mana regeneration", Stat.MANA_REGENERATION);
        lookup.put("spell vamp", Stat.SPELL_VAMP);
    }

    @Override
    public Stat toStat(String statString) {
        return lookup.get(statString);
    }
}
