package thrifty.api.model.effect;

import thrifty.api.model.Stat;

public class Statistic extends UniqueableEffect {
	private Stat stat;
	private int value;
	
	public Stat stat() {
		return stat;
	}
	
	public void stat(Stat stat) {
		this.stat = stat;
	}
	
	public int value() {
		return value;
	}
	
	public void value(int value) {
		this.value = value;
	}
}
