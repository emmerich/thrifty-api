package thrifty.api.model.effect;

public abstract class NamedEffect extends UniqueableEffect {
	private String name;
	
	public String name() {
		return name;
	}
	
	public void name(String name) {
		this.name = name;
	}
}
