package thrifty.api.parser.wikia;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import thrifty.api.model.effect.Passive;
import thrifty.api.parser.Parseable;
import thrifty.api.parser.PassiveParser;

import java.util.HashSet;
import java.util.Set;

@Component
public class WikiaPassiveParser implements PassiveParser<WikiaText> {
    private static final Logger LOG = Logger.getLogger(WikiaPassiveParser.class);

    @Override
    public Set<Passive> extractPassiveSet(WikiaText parseable) {
        LOG.info("Attempting to derive passives from: [" + parseable.getPageName() + "]");

        Set<Passive> result = new HashSet<Passive>();

        try {
            // Passives are contained within the passive property
            String passives = parseable.getProperty("passive");

            // An item can have multiple passives, they are separated by a <br/>
            // SEE: Trinity Force
            String[] individualPassives = passives.split("<br.*/>");

            for(String passive : individualPassives) {
                // Remove all lingering HTML escaped characters:
                passive = passive.replaceAll("&.*;", "");

                String caseInsensitive = passive.toLowerCase();

                LOG.debug("Current passive string: [" + passive + "]");

                Passive current = new Passive();
                boolean isUnique = false;
                String name = null;
                String description = null;

                // A unique Passive is typically formatted as such:
                // Unique Passive - $NAME: $DESCRIPTION
                // SEE: Trinity Force
                if(caseInsensitive.contains("unique:") || caseInsensitive.contains("unique passive:")) {
                    LOG.debug("We are dealing with a passive in the form UNIQUE: $DESCRIPTION");
                    isUnique = true;

                    // Without a name, passives are typically unique to the item they are from.
                    // TODO(emmerich): Double check this
                    name = parseable.getPageName();

                    // Description is everything after the :
                    description = passive.substring(passive.indexOf(":") + 1);

                // UNIQUE: $DESCRIPTION
                // SEE: Athene's Unholy Grail
                } else if(caseInsensitive.contains("unique passive")) {
                    LOG.debug("We are dealing with a passive in the form Unique Passive - $NAME: $DESCRIPTION");
                    isUnique = true;

                    // Unique Passive - $NAME:
                    // Name is between the Unique Passive - and the :
                    int endOfName = passive.indexOf(":");
                    int startOfName = "Unique Passive - ".length();

                    name = parseName(passive.substring(startOfName, endOfName));

                    // Description is everything after the :
                    description = passive.substring(endOfName + 1);

                // UNIQUE - $NAME: $DESCRIPTION
                // SEE: Athene's Unholy Grail
                } else if(caseInsensitive.contains("unique - ")) {
                    LOG.debug("We are dealing with a passive in the form UNIQUE - $NAME: $DESCRIPTION");
                    isUnique = true;

                    int startOfName = "unique - ".length();
                    int endOfName = passive.indexOf(":");

                    name = parseName(passive.substring(startOfName, endOfName));

                    description = passive.substring(endOfName + 1);

                // A non-unique Passive is usually:
                // $DESCRIPTION
                // SEE: Black Cleaver
                } else if(caseInsensitive.contains("unique ")) {
                    LOG.debug("We are dealing with a passive in the form Unique $NAME: $DESCRIPTION");

                    isUnique = true;

                    int startOfName = "unique ".length();
                    int endOfName = passive.indexOf(":");

                    name = parseName(passive.substring(startOfName, endOfName));

                    description = passive.substring(endOfName + 1);
                } else {
                    LOG.debug("We are dealing with a passive in the form $DESCRIPTION");

                    // not unique already set, just grab the description and name.
                    description = passive;

                    name = parseable.getPageName();
                }

                // Take all html and links out of the description:
                description = description.replaceAll("\\[\\[", "").replaceAll("]]", "");

                LOG.debug("Passive parsed with:" +
                        "\n\tName: " + name +
                        "\n\tUnique: " + isUnique +
                        "\n\tDescription: " + description);

                current.name(name);
                current.unique(isUnique);
                current.description(description);

                result.add(current);
            }
        } catch (NoSuchFieldException e) {
            // This item doesn't have any passives
        }


        return result;
    }

    private String parseName(String name) {
        if(name.contains("[[")) {
            if(name.contains("|")) {
                // We are in a link element, the text between the | and ]] is the name
                return name.substring(name.indexOf("|") + 1, name.indexOf("]]"));
            } else {
                return name.substring(2, name.indexOf("]]"));
            }
        } else {
            return name;
        }
    }
}
