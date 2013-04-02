package thrifty.api.model.effect;

import thrifty.api.model.Stat;

import javax.persistence.*;

@Entity
@Table(name = "statistic")
public class Statistic extends UniqueableEffect {

    @Enumerated(EnumType.STRING)
	private Stat stat;

    @Column
	private int value;

    public Statistic(Stat stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public Statistic() {

    }

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
