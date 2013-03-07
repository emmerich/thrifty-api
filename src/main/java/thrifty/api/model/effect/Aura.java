package thrifty.api.model.effect;

public class Aura extends UniqueableEffect {
	private String name;
	private Statistic modifier;
	
	public String name() {
		return name;
	}
	
	public void name(String name) {
		this.name = name;
	}
	
	public Statistic modifier() {
		return modifier;
	}
	
	public void modifier(Statistic modifier) {
		this.modifier = modifier;
	}
}
