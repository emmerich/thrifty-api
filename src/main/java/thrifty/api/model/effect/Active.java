package thrifty.api.model.effect;

public class Active extends UniqueableEffect {
	private int cooldown;
	private String description;
	
	public int cooldown() {
		return cooldown;
	}
	
	public void cooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public String description() {
		return description;
	}
	
	public void description(String description) {
		this.description = description;
	}
}
