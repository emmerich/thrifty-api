package thrifty.api.model.effect;

public class Passive extends NamedEffect {
	private Statistic modifier;
	
	public Statistic modifier() {
		return modifier;
	}
	
	public void modifier(Statistic modifier) {
		this.modifier = modifier;
	}

}
