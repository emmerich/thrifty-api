package thrifty.api.model.effect;

public class Passive extends NamedEffect {

    private String description;


    public String description() {
        return description;
    }

    public void description(String description) {
        this.description = description;
    }
}
