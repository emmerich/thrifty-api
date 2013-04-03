package thrifty.api.parser;

public interface ItemCodeParser<T extends Parseable> {
    public int extractItemCode(T parseable);
}
