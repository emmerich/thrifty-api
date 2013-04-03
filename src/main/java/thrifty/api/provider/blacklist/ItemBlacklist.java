package thrifty.api.provider.blacklist;

import java.util.List;

public interface ItemBlacklist {
    public List<String> blacklist();
    public boolean isBlacklisted(String itemName);
}
