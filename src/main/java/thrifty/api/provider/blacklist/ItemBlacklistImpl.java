package thrifty.api.provider.blacklist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemBlacklistImpl implements ItemBlacklist {

    private List<String> blacklist;

    public ItemBlacklistImpl() {
        blacklist = new ArrayList<String>();

        blacklist.add("Bag of Tea");
        blacklist.add("Scroll of Teleportation");
        blacklist.add("The Hex Core");
        blacklist.add("Blue Pill");
        blacklist.add("Stark's Fervor");
        blacklist.add("Head of Kha'Zix");
    }

    @Override
    public List<String> blacklist() {
        return blacklist;
    }

    @Override
    public boolean isBlacklisted(String itemName) {
        return blacklist.contains(itemName);
    }
}
