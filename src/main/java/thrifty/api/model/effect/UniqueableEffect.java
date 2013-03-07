package thrifty.api.model.effect;

public abstract class UniqueableEffect implements Effect {
	
	private boolean unique;

	public boolean isUnique() {
		return unique;
	}

}
