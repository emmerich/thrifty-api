package thrifty.api.model.effect;

import java.util.Set;

public class Aura extends NamedEffect {
	private Set<Statistic> modifiers;
	
	public Set<Statistic> modifiers() {
		return modifiers;
	}
	
	public void modifier(Set<Statistic> modifiers) {
		this.modifiers = modifiers;
	}
}
